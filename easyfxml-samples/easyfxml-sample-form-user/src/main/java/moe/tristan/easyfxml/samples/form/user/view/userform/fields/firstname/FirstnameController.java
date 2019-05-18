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

package moe.tristan.easyfxml.samples.form.user.view.userform.fields.firstname;

import java.util.Optional;

import org.springframework.stereotype.Component;

import javafx.scene.control.TextField;

import moe.tristan.easyfxml.fxkit.form.FormFieldController;

@Component
public class FirstnameController extends FormFieldController<String> {

    public TextField firstNameField;

    @Override
    public void initialize() {

    }

    @Override
    public String getFieldName() {
        return "First name";
    }

    @Override
    public Class<? extends String> getFieldType() {
        return String.class;
    }

    @Override
    public Optional<String> getFieldValue() {
        return Optional.ofNullable(firstNameField.getText());
    }

}
