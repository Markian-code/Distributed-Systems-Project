package at.technikum.usageservice.service;

import at.technikum.usageservice.model.UsageData;
import at.technikum.usageservice.repository.UsageRepository;
import messagequeue.Message;
import messagequeue.Message.MessageType;
import at.technikum.percentage.model.UpdateMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

class UsageMessageHandlerTest {

    @InjectMocks
    private UsageMessageHandler usageMessageHandler;

    @Mock
    private UsageRepository usageRepository;

    @Mock
    private MyRabbitSender rabbitSender;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleProducerMessage() {
        // Arrange
        LocalDateTime currentHour = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        UsageData existingData = new UsageData(currentHour, 1.0, 0.0, 0.0);

        Message message = new Message();
        message.setType(MessageType.PRODUCER);
        message.setAmount(2.0);

        when(usageRepository.findByHour(currentHour)).thenReturn(Optional.of(existingData));

        // Act
        usageMessageHandler.handleMessage(message);

        // Assert
        assertThat(existingData.getCommunityProduced()).isEqualTo(3.0);
        verify(usageRepository).save(existingData);
        verify(rabbitSender).sendUpdate(any(UpdateMessage.class));
    }

    @Test
    void testHandleUserMessage_FullyCovered() {
        // Arrange
        LocalDateTime currentHour = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        UsageData existingData = new UsageData(currentHour, 5.0, 0.0, 0.0);

        Message message = new Message();
        message.setType(MessageType.USER);
        message.setAmount(2.0);

        when(usageRepository.findByHour(currentHour)).thenReturn(Optional.of(existingData));

        // Act
        usageMessageHandler.handleMessage(message);

        // Assert
        assertThat(existingData.getCommunityProduced()).isEqualTo(3.0);
        assertThat(existingData.getCommunityUsed()).isEqualTo(2.0);
        assertThat(existingData.getGridUsed()).isEqualTo(0.0);

        verify(usageRepository).save(existingData);
        verify(rabbitSender).sendUpdate(any(UpdateMessage.class));
    }

    @Test
    void testHandleUserMessage_NotFullyCovered() {
        // Arrange
        LocalDateTime currentHour = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        UsageData existingData = new UsageData(currentHour, 1.0, 0.0, 0.0);

        Message message = new Message();
        message.setType(MessageType.USER);
        message.setAmount(3.0);

        when(usageRepository.findByHour(currentHour)).thenReturn(Optional.of(existingData));

        // Act
        usageMessageHandler.handleMessage(message);

        // Assert
        assertThat(existingData.getCommunityProduced()).isEqualTo(0.0);
        assertThat(existingData.getCommunityUsed()).isEqualTo(1.0);
        assertThat(existingData.getGridUsed()).isEqualTo(2.0);

        verify(usageRepository).save(existingData);
        verify(rabbitSender).sendUpdate(any(UpdateMessage.class));
    }
}
