/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.    
 */

package org.apache.tuscany.sca.implementation.widget.impl;

import org.apache.tuscany.sca.core.ExtensionPointRegistry;
import org.apache.tuscany.sca.implementation.widget.WidgetImplementation;
import org.apache.tuscany.sca.implementation.widget.WidgetImplementationFactory;

/**
 * Factory for the widget implementation model.
 *
 * @version $Rev: 881960 $ $Date: 2009-11-18 22:08:31 +0000 (Wed, 18 Nov 2009) $
 */
public class WidgetImplementationFactoryImpl implements WidgetImplementationFactory {
    
    private ExtensionPointRegistry registry;
    
    public WidgetImplementationFactoryImpl(ExtensionPointRegistry registry) {
        this.registry = registry;
    }

    public WidgetImplementation createWidgetImplementation() {
        return new WidgetImplementationImpl(registry);
    }

}
