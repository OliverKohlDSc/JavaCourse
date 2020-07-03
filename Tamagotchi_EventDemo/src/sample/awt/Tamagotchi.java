package sample.awt;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

//32x32pix

class Logic{

    public Logic(){
        doSleep();
    }

    int hunger = 0;
    int energy = 0;
    int waste = 0;
    int age = 0;

    // not required
    int happiness = 0;

    public static final int AGE_HATCH = 128;
    public static final int AGE_MATURE = 796;
    public static final int AGE_DEATHFROMNATURALCAUSES = 8192; // :(

    public static final int HUNGER_CANEAT = 32;
    public static final int HUNGER_NEEDSTOEAT = 128;
    //not required
    public static final int HUNGER_SICKFROMNOTEATING = 256;
    public static final int HUNGER_DEADFROMNOTEATING = 512;

    public static final int ENERGY_CANSLEEP = 150;
    public static final int ENERGY_TIERD = 64;
    public static final int ENERGY_PASSOUT = 8;

    public static final int WASTE_EXPUNGE = 256; // lol


    public void doCycle(){
        doRandomEvent();
        hunger++;
        waste++;
        energy--;
        age+=2;
        if(waste>= WASTE_EXPUNGE) happiness--;
    }

    public void doSleep(){
        energy += 256;
    }

    public void doRandomEvent(){

        switch((int)(Math.random()*32)){
            case 12: hunger++;
            case 16: energy--;
            case 18: energy++;
            case 20: waste++;
            case 7: happiness++;
            case 4: happiness--;
            default: break;
        }
    }
}

class AnimationFrame {
    int[] framedata;

    public AnimationFrame(int[] framedata){
        this.framedata = framedata;
    }
    public AnimationFrame(byte[][] drawmatrix){
        framedata = new int[32];
        for(int i = 0; i < 32; i++){
            int shift = 0;
            for(int e = 31; e > -1; e--){
                byte b = drawmatrix[i][e];
                shift = (shift<<1) | b;
            }
            framedata[i] = shift;
        }
    }
}

class Animation{
    AnimationFrame[] animation;
    int frames = 0;
    int frame = 0;
    boolean randomframe = false;
    public Animation(AnimationFrame[] anim){
        animation = anim;
        frames = anim.length;
    }
    public Animation(boolean randomframe, AnimationFrame[] anim){
//        this.randomframe = randomframe; // disabled because I dont like how it acts right now and its not worth fixing yet
        animation = anim;
        frames = anim.length;
    }

    public int getNextFrame(){
        if(randomframe){
            frame = (int)(Math.random()*frames);
        }else{
            frame++;
            if(frame>=frames){
                frame = 0;
            }
        }
        return frame;
    }

    int offset = 0;

    public int getOffset(){
        if(Math.random()>0.5f){
            offset = (int) (Math.random()*6)-3;
        }
        return offset;
    }
}

class Animations{ //I wanted to do this all in a single file, without images "for kicks"
    //I had to "compile" these because this source was exceeding the maximum size for a java file
    public static final Animation IDLE_MATURE = new Animation(true,new AnimationFrame[]{new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0xfc00,0x10200,0x24900,0x20100,0x23100,0x20100,0x20100,0x10200,0xfc00,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0}),new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0xfc00,0x10200,0x28500,0x23100,0x23100,0x20100,0x10200,0xfc00,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0})});
    public static final Animation SLEEP_MATURE = new Animation(true,new AnimationFrame[]{new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x3fc00,0x40200,0x80100}),new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1f800,0x20400,0x40200,0x40200})});
    public static final Animation SLEEP_BABY = new Animation(true,new AnimationFrame[]{new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x78000,0xfc000,0x1fe000}),new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1fe000,0x3ff000})});
    public static final Animation IDLE_BABY = new Animation(true,new AnimationFrame[]{new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x78000,0xb4000,0x1fe000}),new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x78000,0xcc000,0x84000,0xb4000,0x84000,0x78000,0x0})});
    public static final Animation OVERLAY_ZZZ = new Animation(new AnimationFrame[]{new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0xf800000,0x4000000,0x2000000,0x1000000,0xf800000,0x0,0x0,0x3c00000,0x1000000,0x800000,0x3c00000,0x0,0x700000,0x200000,0x700000,0x0,0x80000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0}),new AnimationFrame(new int[]{0x0,0x0,0x0,0xf800000,0x4000000,0x2000000,0x1000000,0xf800000,0x0,0x0,0x3c00000,0x1000000,0x800000,0x3c00000,0x0,0x700000,0x200000,0x700000,0x0,0x80000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0})});
    public static final Animation IDLE_EGG = new Animation(true,new AnimationFrame[]{new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x7e000,0x87000,0x103800,0x300c00,0x700400,0x418200,0x418200,0x400200,0x700600,0x3c0c00,0x1e0800,0x3ffc00,0x0}),new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x7e000,0x87000,0x103800,0x300c00,0x700400,0x400200,0x418200,0x418200,0x700600,0x3c0c00,0xffff00,0x0})});
    public static final Animation OVERLAY_EAT = new Animation(new AnimationFrame[]{new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x4000000,0x2000000,0x7700000,0xff00000,0xfd00000,0xff00000,0x7f00000,0x7e00000,0x3c00000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0}),new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x4000000,0x2000000,0x7700000,0xfe00000,0xfc00000,0xfe00000,0x7f00000,0x7e00000,0x3c00000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0}),new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x4000000,0x2000000,0x7400000,0xf800000,0xf800000,0xf800000,0x7c00000,0x7e00000,0x3c00000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0}),new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x4000000,0x2000000,0x7000000,0xf000000,0xe000000,0xe000000,0x7000000,0x7800000,0x3c00000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0}),new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x4000000,0x2000000,0x1000000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0}),new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0})});
    public static final Animation OVERLAY_STINK = new Animation(new AnimationFrame[]{new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x10000000,0x8000008,0x10000004,0xa000028,0x11000044,0xa000028,0x1000044,0x12000020,0x21000040,0x10000000,0x20000000,0x10000000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0}),new AnimationFrame(new int[]{0x0,0x0,0x0,0x10000000,0x8000008,0x10000004,0xa000028,0x11000044,0xa000028,0x1000044,0x12000020,0x21000040,0x10000000,0x20000000,0x10000000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0})});
    public static final Animation OVERLAY_DEAD = new Animation(new AnimationFrame[]{new AnimationFrame(new int[]{0x0,0x0,0xfc00000,0x1fe00000,0x1b600000,0x1fe00000,0xfc00000,0xfc00000,0x5400000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0}),new AnimationFrame(new int[]{0x0,0x0,0x7e00000,0xff00000,0xdb00000,0xff00000,0x7e00000,0x7e00000,0x2a00000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0})});
    public static final Animation OVERLAY_EXCLAIM = new Animation(new AnimationFrame[]{new AnimationFrame(new int[]{0x0,0x20,0x70,0x70,0x70,0x70,0x70,0x70,0x70,0x20,0x0,0x20,0x70,0x20,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0}),new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0})});
    public static final Animation OVERLAY_CLEAN = new Animation(new AnimationFrame[]{new AnimationFrame(new int[]{0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2,0x2})});
}

class Components{
    static final AnimationFrame SELECTOR = new AnimationFrame(new int[]{0x7800000f,0x60000003,0x40000001,0x40000001,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x40000001,0x40000001,0x60000003,0x7800000f});
    static final AnimationFrame FEED = new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x7805a0,0x7c05a0,0x7c05a0,0x7c05a0,0x7c05a0,0x7c05a0,0x7c05a0,0x7c07e0,0x7c07e0,0x7803c0,0x7803c0,0x7803c0,0x7803c0,0x7803c0,0x7803c0,0x7803c0,0x7803c0,0x7803c0,0x7803c0,0x7803c0,0x7803c0,0x7803c0,0x7803c0,0x300180,0x0,0x0,0x0});
    static final AnimationFrame FLUSH = new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x2000000,0x5000000,0x5000000,0x4800000,0x4800000,0x4400000,0x4400000,0x4400000,0x2200000,0x2200000,0x1200000,0xffff00,0x1200280,0x11ffd00,0x1000080,0x1000080,0x1000080,0x1000080,0x1000080,0x1000080,0x1000040,0xffff80,0x0,0x0,0x0});
    static final AnimationFrame ZZZ = new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0xf800000,0x4000000,0x2000000,0x1000000,0xf800000,0x0,0x0,0x3c00000,0x1000000,0x800000,0x3c00000,0x0,0x700000,0x200000,0x700000,0x0,0x80000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0});
    static final AnimationFrame HEALTH = new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x3ffffc0,0xc000030,0x10912488,0x10912488,0x10492908,0x8000010,0x8000010,0x8000410,0x4000820,0x4001020,0x4002020,0x201c040,0x201c040,0x1ffff80,0x0,0x0,0x0});
    static final AnimationFrame DISPLAY_HUNGER = new AnimationFrame(new int[]{0x0,0x0,0x3bcc94a4,0x4852b4a4,0x39c2d4bc,0x485a94a4,0x4bdc9324,0x0,0x0,0x0,0x0,0x1ffffff8,0x20000004,0x20000004,0x20000004,0x20000004,0x20000004,0x1ffffff8,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0});
    static final AnimationFrame DISPLAY_ENERGY = new AnimationFrame(new int[]{0x0,0x0,0x498ef4bc,0x4a521584,0x704e368c,0x43521484,0x3b92f4bc,0x0,0x0,0x0,0x0,0x1ffffff8,0x20000004,0x20000004,0x20000004,0x20000004,0x20000004,0x1ffffff8,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0});
    static final AnimationFrame DISPLAY_WASTE = new AnimationFrame(new int[]{0x0,0x0,0x7df38e44,0x4405144,0x1c439f44,0x4441154,0x7c439128,0x0,0x0,0x0,0x0,0x1ffffff8,0x20000004,0x20000004,0x20000004,0x20000004,0x20000004,0x1ffffff8,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0});
    static final AnimationFrame DISPLAY_AGE = new AnimationFrame(new int[]{0x0,0x0,0x7ce38,0x5144,0x1c17c,0x5944,0x7de44,0x0,0x0,0x0,0x0,0x1ffffff8,0x20000004,0x20000004,0x20000004,0x20000004,0x20000004,0x1ffffff8,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0});
    static final AnimationFrame DISPLAY_BACK = new AnimationFrame(new int[]{0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x498c710,0x2a52918,0x185e77c,0x2a52918,0x4992710,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0});
}

public class Tamagotchi {

    public static void main(String[] args) {
        JFrame j = new JFrame("Tamagotchi Simulator Thing");
        j.add(new Component() {

            int[] gfxbuffer = new int[32];
            int[] overlaybuffer = new int[32];

            Animation currentAnimation = Animations.IDLE_EGG;
            Animation overlayAnimation = Animations.OVERLAY_ZZZ;

            Logic tom = new Logic();

            int W,H;
            int stage = 0;

            public boolean hasOverlayAnimation = false;

            public final Color BACKGROUND_COLOR = new Color(160, 178, 129);
            public final Color PIXEL_COLOR = new Color(10, 12, 6);
            public final Color NONPIXEL_COLOR = new Color(156, 170, 125);

            BufferedImage components = new BufferedImage(500,520,BufferedImage.TYPE_INT_RGB);
            BufferedImage selector = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);

            public void renderComponent(Graphics2D g, AnimationFrame fr, int xo, int yo){
                for(int x = 0; x < 32; x++){
                    int v = fr.framedata[x];
                    for(int y = 0; y < 32; y++){
                        int bv = (v & (1 << y-1));
                        if(bv != 0)
                            g.setColor(PIXEL_COLOR);
                        else
                            g.setColor(NONPIXEL_COLOR);
                        g.drawLine(y+xo,x+yo,y+xo,x+yo);
                    }
                }
            }
            int off = 0;
            int selid = 0;

            public void compileAnimation(Animation a){
                System.out.print("new Animation(new AnimationFrame[]{");
                for(int q = 0; q < a.frames; q++){
                    System.out.print("new AnimationFrame(new int[]{");
                    for(int i = 0; i < 32; i++){
                        System.out.print("0x"+Integer.toHexString(a.animation[q].framedata[i]) + ",");
                    }
                    System.out.print("}),");
                }
                System.out.println("});");
            }

            public void updatePage(){
                switch(spid){
                    case 0:statsPage = Components.DISPLAY_HUNGER;break;// hunger
                    case 1:statsPage = Components.DISPLAY_AGE;break;// age
                    case 2:statsPage = Components.DISPLAY_WASTE;break;// waste
                    case 3:statsPage = Components.DISPLAY_ENERGY;break;// energy
                    case 4:statsPage = Components.DISPLAY_BACK;break;//back
                    default: break;
                }
            }

            {
                addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        int x = e.getX();
                        int y = e.getY();
                        if(y > 420 && y<484){
                            if(x>64&&x<128){
                                if(stats){
                                    spid--;
                                    if(spid<=-1)spid = 4;
                                    updatePage();
                                }else{
                                    selid--;
                                    if(selid<=-1)selid = 3;
                                }
                            }else
                            if(x>64+96&&x<128+96){
                                centerButton();
                            }else if(x>64+192&&x<128+192){
                                if(stats){
                                    spid++;
                                    spid%=5;
                                    updatePage();
                                }else{
                                    selid++;
                                    selid%=4;
                                }
                            }
                        }
                        repaint();
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }

                });
                W=500;
                H=520;

                setSize(W,H);
                setPreferredSize(new Dimension(W,H));
                gfxbuffer = currentAnimation.animation[0].framedata;
                if(hasOverlayAnimation){
                    overlaybuffer = overlayAnimation.animation[0].framedata;
                }
                Graphics2D gs = selector.createGraphics();
                Graphics2D g = components.createGraphics();
                g.setColor(BACKGROUND_COLOR);
                g.fillRect(0,0,W,H);
                gs.setColor(PIXEL_COLOR);
                int selid = 0;
                for(int x = 0; x < 32; x++){
                    int v = Components.SELECTOR.framedata[x];
                    for(int y = 0; y < 32; y++){
                        int bv = (v & (1 << y-1));
                        if(bv != 0)
                            gs.drawLine(y,x,y,x);
                    }
                }

                renderComponent(g, Components.FEED, 64, 16);
                renderComponent(g, Components.FLUSH, 128, 16);
                renderComponent(g, Components.HEALTH, 192, 16);
                renderComponent(g, Components.ZZZ, 256, 16);

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        while(true){
                            try {
                                if(cleaning)Thread.sleep(100);
                                else
                                    Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Tamagotchi.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            if(stage == 0 && tom.age > Logic.AGE_HATCH){
                                stage++;
                                currentAnimation = Animations.IDLE_BABY;
                                hasOverlayAnimation = false;
                            }
                            if(stage == 1 && tom.age > Logic.AGE_MATURE){
                                stage++;
                                currentAnimation = Animations.IDLE_MATURE;
                            }
                            int frm = overlayAnimation.getNextFrame();
                            if(eating && frm == overlayAnimation.frames-1){
                                eating = false;
                                hasOverlayAnimation = false;
                                overlayAnimation.frame = 0;
                                tom.hunger = 0;
                            }
                            if(hasOverlayAnimation){
                                overlaybuffer = overlayAnimation.animation[frm].framedata;
                            }
                            if(sleeping){
                                tom.energy+=8;
                                if(tom.energy>=256){
                                    sleeping = false;
                                    hasOverlayAnimation = false;
                                    switch(stage){
                                        case 0: currentAnimation = Animations.IDLE_EGG;break;
                                        case 1: currentAnimation = Animations.IDLE_BABY;break;
                                        case 2: currentAnimation = Animations.IDLE_MATURE;break;
                                    }
                                }
                            }
                            if(cleaning){
                                off= cleanincr--;
                                if(off==-33){off=0;cleanincr=0;cleaning = false;hasOverlayAnimation = false;tom.waste = 0;}
                            }else{
                                if(!dead){
                                    gfxbuffer = currentAnimation.animation[currentAnimation.getNextFrame()].framedata;
                                    off = currentAnimation.getOffset();
                                }
                                if(!sleeping&&!dead)tom.doCycle();
                                if(tom.energy<Logic.ENERGY_PASSOUT){if(stage>0){tom.happiness-=64;}triggerSleep();}
                            }
                            if(!sleeping && !cleaning && !eating && !dead){
                                if(tom.waste>=Logic.WASTE_EXPUNGE){
                                    overlayAnimation = Animations.OVERLAY_STINK;
                                    hasOverlayAnimation = true;
                                }else if(tom.energy<=Logic.ENERGY_TIERD || tom.hunger>=Logic.HUNGER_NEEDSTOEAT || tom.waste>=Logic.WASTE_EXPUNGE-Logic.WASTE_EXPUNGE/3){
                                    overlayAnimation = Animations.OVERLAY_EXCLAIM;
                                    hasOverlayAnimation = true;
                                }

                                if(!dead){
                                    if(tom.hunger>=Logic.HUNGER_DEADFROMNOTEATING) triggerDeath();
                                    else if(tom.age>=Logic.AGE_DEATHFROMNATURALCAUSES) triggerDeath();
                                }


                            }
                            repaint();
                        }
                    }

                }).start(); // animation/"cycle" manager
            }

            public final Color BTN_BORDER = new Color(128, 12, 24);
            public final Color BTN_CENTER = new Color(200, 33, 44);

            boolean cleaning = false;
            boolean eating = false;
            boolean stats = false;
            boolean sleeping = false;
            boolean dead = false;

            public void triggerSleep(){
                sleeping = true;
                overlayAnimation = Animations.OVERLAY_ZZZ;
                switch(stage){
                    case 0: break;
                    case 1: currentAnimation = Animations.SLEEP_BABY;break;
                    case 2: currentAnimation = Animations.SLEEP_MATURE;break;
                }
                hasOverlayAnimation = true;
            }

            public void triggerDeath(){
                dead = true;
                overlayAnimation = Animations.OVERLAY_DEAD;
                switch(stage){
                    case 0: break;
                    case 1: currentAnimation = Animations.SLEEP_BABY;break;
                    case 2: currentAnimation = Animations.SLEEP_MATURE;break;
                }
                hasOverlayAnimation = true;
                gfxbuffer = currentAnimation.animation[0].framedata;
                overlaybuffer = overlayAnimation.animation[0].framedata;
                off = 3;
            }

            public void centerButton(){
                if(stage>0 || selid == 2){
                    switch(selid){
                        case 0:
                            eating = true;
                            overlayAnimation = Animations.OVERLAY_EAT;
                            overlayAnimation.frame = 0;
                            hasOverlayAnimation = true;
                            break;
                        case 1:
                            cleaning = true;
                            overlayAnimation = Animations.OVERLAY_CLEAN;
                            overlayAnimation.frame = 0;
                            hasOverlayAnimation = true;
                            break;
                        case 2:
                            stats = !stats;
                            break;
                        case 3:
                            if(tom.energy<=Logic.ENERGY_CANSLEEP){
                                triggerSleep();
                            }
                            break;
                    }
                }

            }

            AnimationFrame statsPage = Components.DISPLAY_HUNGER;
            int spid = 0;

            int cleanincr = 0;
            @Override
            public void paint(Graphics g) {
                g.drawImage(components, 0, 0, null);
                g.drawImage(selector, 64+selid*64, 16, null);
                if(stats){
                    int percv = 0;
                    switch(spid){
                        case 0:
                            percv = tom.hunger*27/Logic.HUNGER_NEEDSTOEAT;
                            break;// hunger
                        case 1:
                            percv = tom.age*27/Logic.AGE_DEATHFROMNATURALCAUSES;
                            break;// age
                        case 2:
                            percv = (tom.waste%Logic.WASTE_EXPUNGE)*27/Logic.WASTE_EXPUNGE;
                            break;// waste
                        case 3:
                            percv = tom.energy*27/256;
                            break;// energy
                        default: break;
                    }
                    if(percv>27)percv = 27;
                    System.out.println(percv);
                    for(int x = 0; x < 32; x++){
                        int v = statsPage.framedata[x];
                        for(int y = 1; y < 33; y++){
                            int bv = (v & (1 << y-1));
                            if(bv != 0 || (percv>0&&x > 11 && y > 3&&x<17&&y<3+percv))
                                g.setColor(PIXEL_COLOR);
                            else
                                g.setColor(NONPIXEL_COLOR);
                            g.fillRect((y-1)*10+32, x*10+64, 8, 8);
                        }
                    }
                }else{
                    int ovr = 0;
                    for(int x = 0; x < 32; x++){
                        int v = gfxbuffer[x];
                        if(hasOverlayAnimation)v |= overlaybuffer[x];
                        for(int y = off; y < 32+off; y++){
                            int bv = (v & (1 << y-1));
                            if(bv != 0)
                                g.setColor(PIXEL_COLOR);
                            else
                                g.setColor(NONPIXEL_COLOR);
                            g.fillRect((y-off)*10+32, x*10+64, 8, 8);
                        }
                    }
                }
                g.setColor(BTN_BORDER);
                g.fillOval(64,420, 64, 64);
                g.fillOval(64+192,420, 64, 64);
                g.fillOval(64+96,420, 64, 64);
                g.setColor(BTN_CENTER);
                g.fillOval(68+96,424, 56, 56);
                g.fillOval(68+192,424, 56, 56);
                g.fillOval(68,424, 56, 56);
                g.setColor(PIXEL_COLOR);
                g.drawOval(64+96,420, 64, 64);
                g.drawOval(64+192,420, 64, 64);
                g.drawOval(64,420, 64, 64);
                g.drawString("DEBUG --", 360,60);
                g.drawString("AGE: " + tom.age, 360,70);
                g.drawString("HUNGER: " + tom.hunger, 360,80);
                g.drawString("ENERGY: " + tom.energy, 360,90);
                g.drawString("WASTE: " + tom.waste, 360,100);
                g.drawString("HAPPINESS: " + tom.happiness, 360,110);
            }
        });
        j.pack();
        j.setLocationRelativeTo(null);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setVisible(true);
    }
}