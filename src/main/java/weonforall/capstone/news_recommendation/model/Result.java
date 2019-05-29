package weonforall.capstone.news_recommendation.model;

import weonforall.capstone.news_recommendation.enums.Status;

import java.util.HashMap;
import java.util.Map;

public class Result {

    private String status;
    private String message;
    private long timestamp;

    Map<String, Object> data;

    public Result(String _status, String _message, String mapKey, Object _data) {
        this.data = new HashMap<>();
        this.status = _status;
        this.message = _message;
        this.data.put(mapKey, _data);
        this.timestamp = System.currentTimeMillis();
    }

    public Result(Status.Key key, Status.Obj obj, String mapKey, Object _data) {
        this.data = new HashMap<>();
        this.status = Integer.toString(( key.getValue() * 100 + obj.getValue() ));
        this.message = key.toString() + obj.toString();
        this.data.put(mapKey, _data);
        this.timestamp = System.currentTimeMillis();
    }

    public Result(Status.Key key, Status.Obj obj) {
        this.status = Integer.toString(( key.getValue() * 100 + obj.getValue() ));
        this.message = key.toString() + obj.toString();
        this.data = new HashMap<>();
        this.timestamp = System.currentTimeMillis();
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Map<String, Object> getData() {
        return data;
    }
    public void setData(Map<String, Object> data) {
        this.data = data;
    }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Result() {
    }

}
