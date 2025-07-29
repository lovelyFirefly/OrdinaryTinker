package com.hoshino.ordinarytinker.Content.Client;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CacheTest {
    public static final Map<UUID, Boolean> CACHE = new ConcurrentHashMap<>();
}
