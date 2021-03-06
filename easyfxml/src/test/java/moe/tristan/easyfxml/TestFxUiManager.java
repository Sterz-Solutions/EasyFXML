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

package moe.tristan.easyfxml;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import moe.tristan.easyfxml.api.FxmlComponent;
import moe.tristan.easyfxml.api.FxmlStylesheet;
import moe.tristan.easyfxml.samples.panewithbutton.PaneWithButtonComponent;

@Component
public class TestFxUiManager extends FxUiManager {

    @Autowired
    private PaneWithButtonComponent paneWithButtonComponent;

    @Override
    protected String title() {
        return "A pane with a button";
    }

    @Override
    protected FxmlComponent mainComponent() {
        return paneWithButtonComponent;
    }

    @Override
    protected Optional<FxmlStylesheet> getStylesheet() {
        return Optional.of(() -> {
            try {
                return Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource("css/sample.css")).toURI());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
