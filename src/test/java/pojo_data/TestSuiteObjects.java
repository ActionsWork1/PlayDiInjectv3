package pojo_data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class TestSuiteObjects {
    @JsonProperty("TestDataObjects")
    private List<TestDataObjects> testData = new ArrayList<>();

    public List<TestDataObjects> getTestData() { return testData; }
    public void setTestData(List<TestDataObjects> testData) { this.testData = testData; }
}
