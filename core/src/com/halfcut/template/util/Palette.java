package com.halfcut.template.util;

import com.badlogic.gdx.graphics.Color;

/**
 * @author halfcutdev
 * @since 22/12/2017
 *
 * All of the colours from @ENDESGA's edg32 palette, in order of hue.
 *
 */
public class Palette {

    static final public Color WHITE     = define(255, 255, 255);
    static final public Color PINE      = define(116,  63,  57);
    static final public Color TETANUS   = define(190,  74,  47);
    static final public Color OAK       = define(184, 111,  80);

    static final public Color SHADESKIN = define(194, 133, 105);
    static final public Color RUST      = define(216, 118,  68);
    static final public Color AMBER     = define(247, 118,  34);
    static final public Color SKIN      = define(232, 183, 150);

    static final public Color SAP       = define(228, 166, 114);
    static final public Color GLOW      = define(254, 174,  52);
    static final public Color BIRCH     = define(234, 212, 170);
    static final public Color LIGHT     = define(254, 231,  97);

    static final public Color GLADE     = define( 99, 199,  77);
    static final public Color FLORA     = define( 62, 137,  72);
    static final public Color MOSS      = define( 38,  92,  66);
    static final public Color MOLD      = define( 25,  60,  62);

    static final public Color ION       = define( 44, 232, 245);
    static final public Color ARCHAEON  = define(  0, 149, 233);
    static final public Color DEEP      = define( 18,  78, 137);
    static final public Color ALUMINIUM = define(192, 203, 220);

    static final public Color ZINC      = define(139, 155, 180);
    static final public Color IRON      = define( 90, 105, 136);
    static final public Color STEEL     = define( 58,  68, 102);
    static final public Color SHADE     = define( 38,  43,  68);

    static final public Color INK       = define( 24,  20,  37);
    static final public Color LILAC     = define(104,  56, 108);
    static final public Color PETAL     = define(181,  80, 136);
    static final public Color DARKBARK  = define( 63,  40,  50);

    static final public Color IIEM      = define(255,   0,  68);
    static final public Color BLOOD     = define(158,  40,  53);
    static final public Color FABRIC    = define(228,  59,  68);
    static final public Color PEACH     = define(246, 117, 122);

    static private Color define(float r, float g, float b) {
        return new Color(r / 255f, g / 255f, b / 255f, 1f);
    }

}