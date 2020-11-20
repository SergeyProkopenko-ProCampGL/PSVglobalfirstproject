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
        psvHashSet.addElement("Hello");
        psvHashSet.addElement("Hi");
        psvHashSet.addElement("Holla");
        psvHashSet.addElement(new String("Holla"));

        psvHashSet2 = new PSVHashSetImpl<Integer>();

        psvHashSet3 = new PSVHashSetImpl<Boolean>();
        psvHashSet3.addElement(true);
        psvHashSet3.addElement(false);

        psvHashSet4 = new PSVHashSetImpl<Double>();
        psvHashSet4.addElement(1.0);
        psvHashSet4.addElement(2.5);
        psvHashSet4.addElement(-0.5);

        psvHashSet5 = new PSVHashSetImpl<Character>();
        psvHashSet5.addElement(null);
        psvHashSet5.addElement('1');
        psvHashSet5.addElement('A');
    }

    @Test()
    public void testAddElement() {
        Assert.assertTrue(psvHashSet.size() == 3);
        psvHashSet5.addElement(null);
        Assert.assertTrue(psvHashSet5.size() == 3);
    }

    @Test()
    public void testRemoveElement() {
        Assert.assertTrue(psvHashSet4.size() == 3);
        psvHashSet4.removeElement(2.5);
        Assert.assertTrue(psvHashSet4.size() == 2);
    }

    @Test()
    public void testContains() {
        Assert.assertTrue(psvHashSet.contains("Hi"));
        Assert.assertFalse(psvHashSet.contains("HI"));
    }

    @Test
    public void testSize() {
        Assert.assertEquals(psvHashSet.size(), 3);
    }

    @Test
    public void testGetElement() {
        Assert.assertTrue("Hi".equalsIgnoreCase((String) psvHashSet.getElement("Hi")));
        Assert.assertNull(psvHashSet5.getElement(null));
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(psvHashSet2.isEmpty());
        psvHashSet2.addElement(10);
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
