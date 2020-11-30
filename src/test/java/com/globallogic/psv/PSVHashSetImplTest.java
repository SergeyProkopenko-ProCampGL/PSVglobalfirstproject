package com.globallogic.psv;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PSVHashSetImplTest {

    private static PSVHashSetImpl<String> psvHashSet;
    private static PSVHashSetImpl<Integer> psvHashSet2;
    private static PSVHashSetImpl<Boolean> psvHashSet3;
    private static PSVHashSetImpl<Double> psvHashSet4;
    private static PSVHashSetImpl<Character> psvHashSet5;

    @BeforeClass
    public static void setPSVHashSetImpl() {
        psvHashSet = new PSVHashSetImpl<String>();
        psvHashSet.add("Hello");
        psvHashSet.add("Hi");
        psvHashSet.add("Holla");
        psvHashSet.add(new String("Holla"));

        psvHashSet2 = new PSVHashSetImpl<Integer>();

        psvHashSet3 = new PSVHashSetImpl<Boolean>();
        psvHashSet3.add(true);
        psvHashSet3.add(false);

        psvHashSet4 = new PSVHashSetImpl<Double>();
        psvHashSet4.add(1.0);
        psvHashSet4.add(2.5);
        psvHashSet4.add(-0.5);

        psvHashSet5 = new PSVHashSetImpl<Character>();
        psvHashSet5.add(null);
        psvHashSet5.add('1');
        psvHashSet5.add('A');
    }

    @Test()
    public void testAdd() {
        Assert.assertEquals(3,psvHashSet.size());
        psvHashSet5.add(null);
        Assert.assertEquals(3, psvHashSet5.size());
    }

    @Test()
    public void testRemove() {
        Assert.assertEquals(3,psvHashSet4.size());
        psvHashSet4.remove(2.5);
        psvHashSet4.add(null);
        psvHashSet4.remove(null);
        Assert.assertEquals(2,psvHashSet4.size());
    }

    @Test()
    public void testContains() {
        Assert.assertTrue(psvHashSet.contains("Hi"));
        Assert.assertFalse(psvHashSet.contains("HI"));
    }

    @Test
    public void testSize() {
        Assert.assertEquals(3, psvHashSet.size());
    }

    @Test
    public void testGetElement() {
        Assert.assertTrue("Hi".equalsIgnoreCase(psvHashSet.getElement("Hi")));
        Assert.assertNull(psvHashSet5.getElement(null));
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(psvHashSet2.isEmpty());
        psvHashSet2.add(10);
        Assert.assertFalse(psvHashSet2.isEmpty());
    }

    @Test
    public void testClear() {
        Assert.assertEquals(2, psvHashSet3.size());
        psvHashSet3.clear();
        Assert.assertEquals(0, psvHashSet3.size());
    }

    @Test
    public void testToString() {
        Assert.assertEquals("{Holla;Hi;Hello}", psvHashSet.toString());
    }

}
