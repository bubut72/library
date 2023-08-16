package com.springbootunleashed.library.domain;

import java.util.Optional;

public record BookSearch(String title, String category, Optional<Boolean> sortByTitle, Optional<Boolean> sortByCategory) { }