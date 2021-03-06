/*
 * Copyright 2017 - 2019 EasyFXML project and contributors
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package moe.tristan.easyfxml.model.system;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import moe.tristan.easyfxml.EasyFxmlAutoConfiguration;
import moe.tristan.easyfxml.junit.SpringBootComponentTest;

import io.vavr.control.Try;

@ContextConfiguration(classes = EasyFxmlAutoConfiguration.class)
@ExtendWith(SpringExtension.class)
public class BrowserSupportTest extends SpringBootComponentTest {

    @Autowired
    private BrowserSupport browserSupport;

    @Test
    public void openUrl_good_url() {
        workaroundOpenJfxHavingAwfulBrowserSupport(() -> browserSupport.openUrl("https://www.google.fr"));
    }

    @Test
    public void openUrl_bad_url() {
        workaroundOpenJfxHavingAwfulBrowserSupport(() -> {
            Try<Void> invalidUrlOpening = browserSupport.openUrl("not_a_url");

            // JFX url opening works for anything, it will just basically try to open it in browser and that's it
            assertThat(invalidUrlOpening.isFailure()).isFalse();
        });
    }

    @Test
    public void browse_ioe() {
        workaroundOpenJfxHavingAwfulBrowserSupport(() -> {
            Try<Void> nullUrlOpening = browserSupport.openUrl((URL) null);

            assertThat(nullUrlOpening.isFailure()).isTrue();
            assertThat(nullUrlOpening.getCause()).isInstanceOf(NullPointerException.class);
        });
    }

    private void workaroundOpenJfxHavingAwfulBrowserSupport(Runnable exec) {
        try {
            exec.run();
        } catch (Exception e) {
            if (e.getClass().equals(Exception.class) && e.getMessage().equals("No web browser found")) {
                throw new IllegalStateException("Tests require the installation of at least one valid web browser. See HostServicesDelegate#browsers");
            } else {
                throw e;
            }
        }
    }

}
