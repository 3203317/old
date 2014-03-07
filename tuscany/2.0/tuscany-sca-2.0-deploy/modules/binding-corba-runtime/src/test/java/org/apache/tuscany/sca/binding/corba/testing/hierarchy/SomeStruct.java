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

package org.apache.tuscany.sca.binding.corba.testing.hierarchy;

/**
 * @version $Rev: 671373 $ $Date: 2008-06-24 23:30:14 +0100 (Tue, 24 Jun 2008) $
 */
public class SomeStruct {

    public SimpleStruct innerStruct;
    public String str;
    public String[] str_list;
    public int[][] twoDimSeq;
    public int[][][] threeDimSeq;

}
