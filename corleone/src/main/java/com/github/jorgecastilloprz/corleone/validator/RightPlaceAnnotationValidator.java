/*
 * Copyright (C) 2015 Jorge Castillo Pérez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jorgecastilloprz.corleone.validator;

import com.github.jorgecastilloprz.corleone.messager.ErrorMessager;
import java.util.Set;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

/**
 * Common logic for all the right place checking validators
 *
 * @author Jorge Castillo Pérez
 */
public abstract class RightPlaceAnnotationValidator extends AnnotationValidator {

  public RightPlaceAnnotationValidator(RoundEnvironment roundEnvironment,
      ErrorMessager errorMessager, Class annotation) {
    super(roundEnvironment, errorMessager, annotation);
  }

  @Override public boolean validate() {
    Set<? extends Element> annotatedElems = roundEnvironment.getElementsAnnotatedWith(annotation);
    return validateElements(annotatedElems, annotation.getSimpleName());
  }

  private boolean validateElements(Set<? extends Element> elementsToValidate, String annotation) {
    for (Element currentElement : elementsToValidate) {
      if (currentElement.getKind() != getElementKind()) {
        errorMessager.error("@%s annotation must be used to qualify elements of kind: %s.", annotation, getElementKind());
        return false;
      }
    }
    return true;
  }
  
  protected abstract ElementKind getElementKind();
}
