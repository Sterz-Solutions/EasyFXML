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

package moe.tristan.easyfxml.natives.settings.platforms.windows;

import java.util.Optional;

import moe.tristan.easyfxml.natives.settings.Setting;
import moe.tristan.easyfxml.natives.settings.SettingsRepository;

public class WindowsSystemSettingsRepository implements SettingsRepository {

    @Override
    public <T> Optional<Setting<T>> getSetting(String settingName, Class<? extends T> valueType) {
        return Optional.empty();
    }

    @Override
    public void saveSetting(Setting<?> setting) {

    }

}
