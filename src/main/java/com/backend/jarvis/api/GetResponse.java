package com.backend.jarvis.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetResponse<T> {
    private int count;
    private T data;
}
