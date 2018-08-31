package no.nav.atom.fleischer.curlservice.repository.mongo;

import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class ResponseDocument extends Document {
    private LocalDateTime dateTime;
    private ZoneId zoneId;
    private String avgTimeNameLookup;
    private String avgTimeConnect;
    private String avgTimeAppConnect;
    private String avgTimePreTransfer;
    private String avgTimeRedirect;
    private String avgTimeStartTransfer;
    private String avgTimeTotal;

    public ResponseDocument() {
        super();
    }

    public ResponseDocument(String url,
                            LocalDateTime dateTime,
                            ZoneId zoneId,
                            String avgTimeNameLookup,
                            String avgTimeConnect,
                            String avgTimeAppConnect,
                            String avgTimePreTransfer,
                            String avgTimeRedirect,
                            String avgTimeStartTransfer,
                            String avgTimeTotal) {
        super(new ObjectId());
        this.setUrl(url);
        this.dateTime = dateTime;
        this.zoneId = zoneId;
        this.avgTimeNameLookup = avgTimeNameLookup;
        this.avgTimeConnect = avgTimeConnect;
        this.avgTimeAppConnect = avgTimeAppConnect;
        this.avgTimePreTransfer = avgTimePreTransfer;
        this.avgTimeRedirect = avgTimeRedirect;
        this.avgTimeStartTransfer = avgTimeStartTransfer;
        this.avgTimeTotal = avgTimeTotal;
    }

    LocalDateTime getLocalDateTime() {
        return dateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.dateTime = localDateTime.withNano(0);
    }

    String getAvgTimeNameLookup() {
        return avgTimeNameLookup;
    }

    public void setAvgTimeNameLookup(String avgTimeNameLookup) {
        this.avgTimeNameLookup = avgTimeNameLookup;
    }

    String getAvgTimeConnect() {
        return avgTimeConnect;
    }

    public void setAvgTimeConnect(String avgTimeConnect) {
        this.avgTimeConnect = avgTimeConnect;
    }

    String getAvgTimeAppConnect() {
        return avgTimeAppConnect;
    }

    public void setAvgTimeAppConnect(String avgTimeAppConnect) {
        this.avgTimeAppConnect = avgTimeAppConnect;
    }

    String getAvgTimePreTransfer() {
        return avgTimePreTransfer;
    }

    public void setAvgTimePreTransfer(String avgTimePreTransfer) {
        this.avgTimePreTransfer = avgTimePreTransfer;
    }

    String getAvgTimeRedirect() {
        return avgTimeRedirect;
    }

    public void setAvgTimeRedirect(String avgTimeRedirect) {
        this.avgTimeRedirect = avgTimeRedirect;
    }

    String getAvgTimeStartTransfer() {
        return avgTimeStartTransfer;
    }

    public void setAvgTimeStartTransfer(String avgTimeStartTransfer) {
        this.avgTimeStartTransfer = avgTimeStartTransfer;
    }

    String getAvgTimeTotal() {
        return avgTimeTotal;
    }

    public void setAvgTimeTotal(String avgTimeTotal) {
        this.avgTimeTotal = avgTimeTotal;
    }

    ZoneId getZoneId() {
        return zoneId;
    }

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseDocument that = (ResponseDocument) o;
        return Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.getUrl(), that.getUrl()) &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(zoneId, that.zoneId) &&
                Objects.equals(avgTimeNameLookup, that.avgTimeNameLookup) &&
                Objects.equals(avgTimeConnect, that.avgTimeConnect) &&
                Objects.equals(avgTimeAppConnect, that.avgTimeAppConnect) &&
                Objects.equals(avgTimePreTransfer, that.avgTimePreTransfer) &&
                Objects.equals(avgTimeRedirect, that.avgTimeRedirect) &&
                Objects.equals(avgTimeStartTransfer, that.avgTimeStartTransfer) &&
                Objects.equals(avgTimeTotal, that.avgTimeTotal);
    }

    @Override
    public int hashCode() {

        return Objects.hash(this.getId(), this.getUrl(), dateTime, zoneId, avgTimeNameLookup, avgTimeConnect, avgTimeAppConnect, avgTimePreTransfer, avgTimeRedirect, avgTimeStartTransfer, avgTimeTotal);
    }

    @Override
    public String toString() {
        return "ResponseDocument{" +
                "id=" + this.getId() +
                ", url='" + this.getUrl() + '\'' +
                ", dateTime=" + dateTime +
                ", zoneId=" + zoneId +
                ", avgTimeNameLookup='" + avgTimeNameLookup + '\'' +
                ", avgTimeConnect='" + avgTimeConnect + '\'' +
                ", avgTimeAppConnect='" + avgTimeAppConnect + '\'' +
                ", avgTimePreTransfer='" + avgTimePreTransfer + '\'' +
                ", avgTimeRedirect='" + avgTimeRedirect + '\'' +
                ", avgTimeStartTransfer='" + avgTimeStartTransfer + '\'' +
                ", avgTimeTotal='" + avgTimeTotal + '\'' +
                '}';
    }
}
