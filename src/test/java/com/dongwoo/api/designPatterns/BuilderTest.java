package com.dongwoo.api.designPatterns;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BuilderTest {

    @DisplayName("Builder pattern")
    @Test
    void main() {
        MobileBuilder builder = new MobileBuilder();
        Mobile myMobile = builder.with(myBuilder -> {
            myBuilder.ram = 4;
            myBuilder.battery = 4000;
            myBuilder.processor = "A12";
        }).createMobile();

        assertThat(myMobile.getRam(), is(4));
        assertThat(myMobile.getBattery(), is(4000));
        assertThat(myMobile.getProcessor(), is("A12"));
    }

    @Data
    @AllArgsConstructor
    class Mobile {

        private final int ram;
        private final int storage;
        private final int battery;
        private final int camera;
        private final String processor;
        private final double screenSize;

        public Mobile(MobileBuilder builder) {
            this.ram = builder.ram;
            this.storage = builder.storage;
            this.battery = builder.battery;
            this.camera = builder.camera;
            this.processor = builder.processor;
            this.screenSize = builder.screenSize;
        }
    }

    @Data
    class MobileBuilder {

        private int ram; /* if final, Default Constructor Error */
        private int storage;
        private int battery;
        private int camera;
        private String processor;
        private double screenSize;

        public MobileBuilder with(Consumer<MobileBuilder> buildFields) {
            buildFields.accept(this);
            return this;
        }

        public Mobile createMobile() {
            return new Mobile(this);
        }
    }
}
