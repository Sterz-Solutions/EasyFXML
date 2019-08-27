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

import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.context.ApplicationContext;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import moe.tristan.easyfxml.api.FxmlComponent;
import moe.tristan.easyfxml.api.FxmlController;
import moe.tristan.easyfxml.model.beanmanagement.ControllerManager;
import moe.tristan.easyfxml.model.beanmanagement.Selector;
import moe.tristan.easyfxml.model.fxml.FxmlLoadResult;

import io.vavr.control.Try;

/**
 * This class is the base entry point of the library.
 * <p>
 * It is expected to be acquired from the Spring {@link ApplicationContext}.
 *
 * @see Try
 * @see FxmlComponent
 * @see FxmlLoadResult
 */
public interface EasyFxml {

    /**
     * Loads a {@link FxmlComponent} as a Pane (this is the safest base class for all sorts of hierarchies) since most of the base containers are subclasses of
     * it.
     * <p>
     * It also registers its controller in the {@link ControllerManager} for later usage.
     * <p>
     * It returns a {@link Try} which is a monadic structure which allows us to do clean exception-handling.
     *
     * @param component The element's {@link FxmlComponent} counterpart.
     *
     * @return A {@link Try} containing either the JavaFX {@link Pane} inside a {@link Try.Success} or the exception that was raised during the chain of calls
     * needed to load it, inside a {@link Try.Failure}. See {@link Try#getOrElse(Object)}, {@link Try#onFailure(Consumer)}, {@link Try#recover(Function)} and
     * related methods for how to handle {@link Try.Failure}.
     */
    FxmlLoadResult<Pane, FxmlController> load(final FxmlComponent component);

    /**
     * Same as {@link #load(FxmlComponent)} but offers a selector parameter for multistage usage of {@link ControllerManager}.
     *
     * @param component The element's {@link FxmlComponent} counterpart.
     * @param selector  The selector for deduplication of Panes sharing the same {@link FxmlComponent}.
     *
     * @return A Try of the {@link Pane} to be loaded. See {@link #load(FxmlComponent)} for more information on {@link Try}.
     */
    FxmlLoadResult<Pane, FxmlController> load(final FxmlComponent component, final Selector selector);

    /**
     * Same as {@link #load(FxmlComponent)} except you can choose the return type wished for instead of just {@link Pane}.
     * <p>
     * It's a bad idea but it can sometimes be actually useful on the rare case where the element is really nothing like a Pane. Be it a very complex button or
     * a custom textfield with non-rectangular shape.
     *
     * @param component       The element's {@link FxmlComponent} counterpart.
     * @param nodeClass       The class of the JavaFX {@link Node} represented by this {@link FxmlComponent}.
     * @param controllerClass The class of the {@link FxmlController} managing this {@link FxmlComponent}.
     * @param <NODE>          The type of the node. Mostly for type safety.
     * @param <CONTROLLER>    The type of the controller. Mostly for type safety.
     *
     * @return A {@link FxmlLoadResult} containing two {@link Try} instances. One for the node itself and one for its controller. This allows for granular load
     * error management.
     */
    <NODE extends Node, CONTROLLER extends FxmlController>
    FxmlLoadResult<NODE, CONTROLLER>
    load(
        final FxmlComponent component,
        final Class<? extends NODE> nodeClass,
        final Class<? extends CONTROLLER> controllerClass
    );

    /**
     * This is to {@link #load(FxmlComponent, Class, Class)} just what {@link #load(FxmlComponent, Selector)} is to {@link #load(FxmlComponent)}.
     *
     * @param component       The element's {@link FxmlComponent} counterpart.
     * @param nodeClass       The class of the JavaFX {@link Node} represented by this {@link FxmlComponent}.
     * @param controllerClass The class of the {@link FxmlController} managing this {@link FxmlComponent}.
     * @param selector        The selector for deduplication of Panes sharing the same {@link FxmlComponent}.
     * @param <NODE>          The type of the node. Mostly for type safety.
     * @param <CONTROLLER>    The type of the controller. Mostly for type safety.
     *
     * @return A {@link FxmlLoadResult} containing two {@link Try} instances.
     *
     * One for the node itself and one for its controller. This allows for granular load error management.
     */
    <NODE extends Node, CONTROLLER extends FxmlController>
    FxmlLoadResult<NODE, CONTROLLER>
    load(
        final FxmlComponent component,
        final Class<? extends NODE> nodeClass,
        final Class<? extends CONTROLLER> controllerClass,
        final Selector selector
    );

}
