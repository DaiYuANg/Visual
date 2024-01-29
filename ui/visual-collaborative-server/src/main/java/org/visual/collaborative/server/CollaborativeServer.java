package org.visual.collaborative.server;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@Getter
public class CollaborativeServer {
    public static void main(String[] args) {
       new InternalServer().start();
    }
}
