package io.sewahshop.orderservice.dto;

public record Book(
        String isbn,
        String title,
        String author,
        Double price
){}

