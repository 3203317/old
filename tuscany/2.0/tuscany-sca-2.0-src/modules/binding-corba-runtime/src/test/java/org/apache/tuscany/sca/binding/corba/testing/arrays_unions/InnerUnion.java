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
package org.apache.tuscany.sca.binding.corba.testing.arrays_unions;


/**
* org/apache/tuscany/sca/binding/corba/testing/arrays_unions/InnerUnion.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from arrays_unions.idl
*/

public final class InnerUnion implements org.omg.CORBA.portable.IDLEntity
{
  private int ___x;
  private float ___y;
  private int __discriminator;
  private boolean __uninitialized = true;

  public InnerUnion ()
  {
  }

  public int discriminator ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    return __discriminator;
  }

  public int x ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyx (__discriminator);
    return ___x;
  }

  public void x (int value)
  {
    __discriminator = 1;
    ___x = value;
    __uninitialized = false;
  }

  public void x (int discriminator, int value)
  {
    verifyx (discriminator);
    __discriminator = discriminator;
    ___x = value;
    __uninitialized = false;
  }

  private void verifyx (int discriminator)
  {
    if (discriminator != 1)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public float y ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyy (__discriminator);
    return ___y;
  }

  public void y (float value)
  {
    __discriminator = 2;
    ___y = value;
    __uninitialized = false;
  }

  public void y (int discriminator, float value)
  {
    verifyy (discriminator);
    __discriminator = discriminator;
    ___y = value;
    __uninitialized = false;
  }

  private void verifyy (int discriminator)
  {
    if (discriminator != 2)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public void _default ()
  {
    __discriminator = -2147483648;
    __uninitialized = false;
  }

  public void _default (int discriminator)
  {
    verifyDefault( discriminator ) ;
    __discriminator = discriminator ;
    __uninitialized = false;
  }

  private void verifyDefault( int value )
  {
    switch (value) {
      case 1:
      case 2:
        throw new org.omg.CORBA.BAD_OPERATION() ;

      default:
        return;
    }
  }

} // class InnerUnion
