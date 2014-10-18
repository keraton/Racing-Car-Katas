package tddmicroexercises.telemetrysystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TelemetryDiagnosticControlsTest {

    private static final String CORRECT_DIAGNOSTIC_MESSAGE_RESULT =
                                                    "LAST TX rate................ 100 MBPS\r\n"
                                                    + "HIGHEST TX rate............. 100 MBPS\r\n"
                                                    + "LAST RX rate................ 100 MBPS\r\n"
                                                    + "HIGHEST RX rate............. 100 MBPS\r\n"
                                                    + "BIT RATE.................... 100000000\r\n"
                                                    + "WORD LEN.................... 16\r\n"
                                                    + "WORD/FRAME.................. 511\r\n"
                                                    + "BITS/FRAME.................. 8192\r\n"
                                                    + "MODULATION TYPE............. PCM/FM\r\n"
                                                    + "TX Digital Los.............. 0.75\r\n"
                                                    + "RX Digital Los.............. 0.10\r\n"
                                                    + "BEP Test.................... -5\r\n"
                                                    + "Local Rtrn Count............ 00\r\n"
                                                    + "Remote Rtrn Count........... 00";

    @Mock
    private TelemetryClient mockTelemetryClient;


	@Test
    public void checkTransmission_should_send_a_diagnostic_message_and_receive_a_status_message_response() throws Exception {
        TelemetryDiagnosticControls telemetryDiagnosticControls = new TelemetryDiagnosticControls();

        telemetryDiagnosticControls.checkTransmission();

        assertThat(telemetryDiagnosticControls.getDiagnosticInfo()).isEqualTo(CORRECT_DIAGNOSTIC_MESSAGE_RESULT);
    }

    @Test
    public void should_throw_exception_after_three_connections_failed() {
        TelemetryDiagnosticControls telemetryDiagnosticControls = new TelemetryDiagnosticControls(mockTelemetryClient);
        when(mockTelemetryClient.getOnlineStatus()).thenReturn(false);

        try {

            telemetryDiagnosticControls.checkTransmission();

            fail("Should launch exception before");
        } catch (Exception e) {}

        verify(mockTelemetryClient, times(3)).connect(anyString());

    }

    @Test
    public void should_getDiagnosticInfo_when_connections_failed_less_than_3() throws Exception {
        TelemetryDiagnosticControls telemetryDiagnosticControls = new TelemetryDiagnosticControls(mockTelemetryClient);
        when(mockTelemetryClient.getOnlineStatus()).thenAnswer(
                new Answer<Object>() {
                    int called = 0;
                    @Override
                    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                        called++;
                        if (called==1) {
                            return Boolean.FALSE;
                        }
                        return Boolean.TRUE;
                    }
                });
        when(mockTelemetryClient.receive()).thenReturn(CORRECT_DIAGNOSTIC_MESSAGE_RESULT);

        telemetryDiagnosticControls.checkTransmission();

        assertThat(telemetryDiagnosticControls.getDiagnosticInfo()).isEqualTo(CORRECT_DIAGNOSTIC_MESSAGE_RESULT);
    }

}
