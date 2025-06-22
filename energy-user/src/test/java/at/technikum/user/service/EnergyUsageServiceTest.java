package at.technikum.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnergyUsageServiceTest {

    private EnergyUsageService service;

    @BeforeEach
    void setUp() {
        service = new EnergyUsageService();
    }

    @Test
    void calculateUsageBasedOnTime_shouldReturnValidRange() {
        // Wir rufen die private Methode indirekt über Reflection (weil sie private ist)
        // oder wir machen sie package-private (ohne "private") zum Testen

        // Vereinfachte Variante: wir extrahieren die Methode public:

        double usage = serviceTestableCalculateUsage();

        assertTrue(usage >= 0.001, "Usage should be >= 0.001");
        assertTrue(usage <= 0.0125, "Usage should be <= 0.0125 (max in peak hours)");
    }

    // Dummy-Methode zum Testen → in echter Klasse machst du calculateUsageBasedOnTime() "protected" oder "package-private"
    private double serviceTestableCalculateUsage() {
        try {
            var method = EnergyUsageService.class.getDeclaredMethod("calculateUsageBasedOnTime");
            method.setAccessible(true);
            return (double) method.invoke(service);
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
            return 0.0;
        }
    }
}
