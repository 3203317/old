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

package org.apache.tuscany.sca.binding.corba.testing.generated;

/**
* org/apache/tuscany/sca/binding/corba/testing/generated/long_long_listHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from general_tests.idl
* monday, 23 june 2008 14:12:28 CEST
*/

abstract public class long_long_listHelper {
    private static String _id = "IDL:org/apache/tuscany/sca/binding/corba/testing/generated/long_long_list:1.0";

    public static void insert(org.omg.CORBA.Any a, long[] that) {
        org.omg.CORBA.portable.OutputStream out = a.create_output_stream();
        a.type(type());
        write(out, that);
        a.read_value(out.create_input_stream(), type());
    }

    public static long[] extract(org.omg.CORBA.Any a) {
        return read(a.create_input_stream());
    }

    private static org.omg.CORBA.TypeCode __typeCode = null;

    synchronized public static org.omg.CORBA.TypeCode type() {
        if (__typeCode == null) {
            __typeCode = org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.tk_longlong);
            __typeCode = org.omg.CORBA.ORB.init().create_sequence_tc(0, __typeCode);
            __typeCode =
                org.omg.CORBA.ORB.init()
                    .create_alias_tc(org.apache.tuscany.sca.binding.corba.testing.generated.long_long_listHelper.id(),
                                     "long_long_list",
                                     __typeCode);
        }
        return __typeCode;
    }

    public static String id() {
        return _id;
    }

    public static long[] read(org.omg.CORBA.portable.InputStream istream) {
        long value[] = null;
        int _len0 = istream.read_long();
        value = new long[_len0];
        istream.read_longlong_array(value, 0, _len0);
        return value;
    }

    public static void write(org.omg.CORBA.portable.OutputStream ostream, long[] value) {
        ostream.write_long(value.length);
        ostream.write_longlong_array(value, 0, value.length);
    }

}
