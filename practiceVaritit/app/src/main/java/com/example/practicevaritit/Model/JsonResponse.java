package com.example.practicevaritit.Model;

import java.util.Arrays;

public class JsonResponse {
    private User[] results;

    public User[] getResults() {
        return results;
    }

    public void setResults(User[] results) {
        this.results = results;
    }

    public JsonResponse(User[] results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "JsonResponse{" +
                "results=" + Arrays.toString(results) +
                '}';
    }
}
