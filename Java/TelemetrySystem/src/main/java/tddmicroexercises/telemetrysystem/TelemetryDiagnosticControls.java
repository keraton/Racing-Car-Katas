package tddmicroexercises.telemetrysystem;

import static java.util.stream.IntStream.range;

public class TelemetryDiagnosticControls {
    public static final int RETRY_MAX = 3;

    private final String DiagnosticChannelConnectionString = "*111#";
    private final TelemetryClient telemetryClient;

    private String diagnosticInfo = "";

    public TelemetryDiagnosticControls() {
        this(new TelemetryClientImpl());
    }

    public TelemetryDiagnosticControls(TelemetryClient telemetryClient) {
        this.telemetryClient = telemetryClient;
    }

    public String getDiagnosticInfo() {
        return diagnosticInfo;
    }

    public void checkTransmission() throws Exception {
        diagnosticInfo = "";
        telemetryClient.disconnect();

        range(0, RETRY_MAX)
                .filter(a -> isStillOffline())
                .forEach(a -> telemetryClient.connect(DiagnosticChannelConnectionString));

        if (isStillOffline())
            throw new Exception("Unable to connect.");

        telemetryClient.send(TelemetryClientImpl.DIAGNOSTIC_MESSAGE);
        diagnosticInfo = telemetryClient.receive();
    }

    private boolean isStillOffline() {
        return telemetryClient.getOnlineStatus() == false;
    }

}
