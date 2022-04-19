package com.newworldcompanytracker.service.task;

import java.util.function.BiFunction;

public interface Mappers<T, R, S> extends BiFunction<T, R, S> {
}
