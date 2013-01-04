/*
 * Copyright 2005-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dozer.functional_tests;

import org.dozer.DozerBeanMapper;
import org.dozer.vo.proto.LiteTestObject;
import org.dozer.vo.proto.ProtoTestObjects.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author tierney.matt
 * @author garsombke.franz
 */
public class DeepMappingTest extends ProtoAbstractTest {
  private DozerBeanMapper mapper;

  @Before
  public void setUp() throws Exception {
    mapper = getMapper("protoSrcDeepBeansMapping.xml");
  }

  @Test
  public void protoSrc_copySimpleOneLevelField() {
    final String ONE_VALUE = "smthOne";

    ProtoTestObjectWithNestedProtoObject.Builder builder = ProtoTestObjectWithNestedProtoObject.newBuilder();
    SimpleProtoTestObject.Builder nestedObjectBuilder = SimpleProtoTestObject.newBuilder();
    nestedObjectBuilder.setOne(ONE_VALUE);
    builder.setNestedObject(nestedObjectBuilder.build());
    builder.setOne("smthAnother-neverMind");
    ProtoTestObjectWithNestedProtoObject src = builder.build();
    LiteTestObject result = mapper.map(src, LiteTestObject.class);
    assertEquals(ONE_VALUE, result.getOne());
  }
}