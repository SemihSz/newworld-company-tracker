package com.newworldcompanytracker.service.task;

import java.util.function.Function;

public interface SimpleTask<T, R> extends Function<T, R> {
}
