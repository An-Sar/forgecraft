package nmd.primal.forgecraft.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPlateHelmet extends ModelBiped
{
  //fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    private ModelRenderer Shape1;
    private ModelRenderer Shape2;
    private ModelRenderer Shape3;
    private ModelRenderer Shape4;
    private ModelRenderer Shape5;
    private ModelRenderer Shape6;
    private ModelRenderer Shape7;
    private ModelRenderer Shape8;
    private ModelRenderer Shape9;
    private ModelRenderer Shape10;
    private ModelRenderer Shape11;
    private ModelRenderer Shape12;
    private ModelRenderer Shape13;
    private ModelRenderer Shape14;
    private ModelRenderer Shape15;
    private ModelRenderer Shape16;
    private ModelRenderer Shape17;
    private ModelRenderer Shape18;
    private ModelRenderer Shape19;
    private ModelRenderer Shape20;
    private ModelRenderer Shape21;
  
  public ModelPlateHelmet(float scale)
  {
    //super(1.0f, 64, 64);
    super(scale, 0.0f, 64, 64);
    //textureWidth = 64;
    //textureHeight = 64;
    
/*
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-4F, -8F, -4F, 8, 8, 8);
      head.setRotationPoint(0F, 0F, 0F);
      head.setTextureSize(64, 64);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      /*
      body = new ModelRenderer(this, 16, 16);
      body.addBox(-4F, 0F, -2F, 8, 12, 4);
      body.setRotationPoint(0F, 0F, 0F);
      body.setTextureSize(64, 64);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      rightarm = new ModelRenderer(this, 40, 16);
      rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
      rightarm.setRotationPoint(-5F, 2F, 0F);
      rightarm.setTextureSize(64, 64);
      rightarm.mirror = true;
      setRotation(rightarm, 0F, 0F, 0F);
      leftarm = new ModelRenderer(this, 40, 16);
      leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
      leftarm.setRotationPoint(5F, 2F, 0F);
      leftarm.setTextureSize(64, 64);
      leftarm.mirror = true;
      setRotation(leftarm, 0F, 0F, 0F);
      rightleg = new ModelRenderer(this, 0, 16);
      rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
      rightleg.setRotationPoint(-2F, 12F, 0F);
      rightleg.setTextureSize(64, 64);
      rightleg.mirror = true;
      setRotation(rightleg, 0F, 0F, 0F);
      leftleg = new ModelRenderer(this, 0, 16);
      leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
      leftleg.setRotationPoint(2F, 12F, 0F);
      leftleg.setTextureSize(64, 64);
      leftleg.mirror = true;
      setRotation(leftleg, 0F, 0F, 0F);
      */
    Shape1 = new ModelRenderer(this, 0, 37);
    Shape1.addBox(-4.5F, -7F, -5F, 4, 7, 1);
    Shape1.setRotationPoint(0F, 0F, 0F);
    Shape1.setTextureSize(64, 64);
    Shape1.mirror = false;
    setRotation(Shape1, 0F, 1.570796F, 0F);
    Shape2 = new ModelRenderer(this, 52, 37);
    Shape2.addBox(-4.5F, -7F, -5F, 4, 2, 1);
    Shape2.setRotationPoint(0F, 0F, 0F);
    Shape2.setTextureSize(64, 64);
    Shape2.mirror = false;
    setRotation(Shape2, 0F, 0F, 0F);
    Shape3 = new ModelRenderer(this, 52, 40);
    Shape3.addBox(-2F, -7F, -5.5F, 4, 2, 1);
    Shape3.setRotationPoint(0F, 0F, 0F);
    Shape3.setTextureSize(64, 64);
    Shape3.mirror = false;
    setRotation(Shape3, 0F, 0F, 0F);
    Shape4 = new ModelRenderer(this, 52, 52);
    Shape4.addBox(0.5F, -7F, -5F, 4, 2, 1);
    Shape4.setRotationPoint(0F, 0F, 0F);
    Shape4.setTextureSize(64, 64);
    Shape4.mirror = false;
    setRotation(Shape4, 0F, 0F, 0F);
    Shape5 = new ModelRenderer(this, 0, 37);
    Shape5.addBox(0.5F, -7F, -5F, 4, 5, 1);
    Shape5.setRotationPoint(0F, 0F, 0F);
    Shape5.setTextureSize(64, 64);
    Shape5.mirror = false;
    setRotation(Shape5, 0F, 1.570796F, 0F);
    Shape7 = new ModelRenderer(this, 5, 37);
    Shape7.addBox(-2F, -7F, -5.5F, 4, 6, 1);
    Shape7.setRotationPoint(0F, 0F, 0F);
    Shape7.setTextureSize(64, 64);
    Shape7.mirror = false;
    setRotation(Shape7, 0F, 1.570796F, 0F);
    Shape8 = new ModelRenderer(this, 2, 37);
    Shape8.addBox(-2F, -7F, -5.5F, 4, 8, 1);
    Shape8.setRotationPoint(0F, 0F, 0F);
    Shape8.setTextureSize(64, 64);
    Shape8.mirror = false;
    setRotation(Shape8, 0F, 3.141593F, 0F);
    Shape9 = new ModelRenderer(this, 5, 37);
    Shape9.addBox(0.5F, -7F, -5F, 4, 7, 1);
    Shape9.setRotationPoint(0F, 0F, 0F);
    Shape9.setTextureSize(64, 64);
    Shape9.mirror = false;
    setRotation(Shape9, 0F, 3.141593F, 0F);
    Shape10 = new ModelRenderer(this, 20, 37);
    Shape10.addBox(-4.5F, -7F, -5F, 4, 5, 1);
    Shape10.setRotationPoint(0F, 0F, 0F);
    Shape10.setTextureSize(64, 64);
    Shape10.mirror = false;
    setRotation(Shape10, 0F, -1.570796F, 0F);
    Shape11 = new ModelRenderer(this, 25, 37);
    Shape11.addBox(-2F, -7F, -5.5F, 4, 6, 1);
    Shape11.setRotationPoint(0F, 0F, 0F);
    Shape11.setTextureSize(64, 64);
    Shape11.mirror = false;
    setRotation(Shape11, 0F, -1.570796F, 0F);
    Shape12 = new ModelRenderer(this, 30, 37);
    Shape12.addBox(0.5F, -7F, -5F, 4, 7, 1);
    Shape12.setRotationPoint(0F, 0F, 0F);
    Shape12.setTextureSize(64, 64);
    Shape12.mirror = false;
    setRotation(Shape12, 0F, -1.570796F, 0F);
    Shape13 = new ModelRenderer(this, 10, 55);
    Shape13.addBox(-2F, -11F, -2F, 4, 1, 4);
    Shape13.setRotationPoint(0F, 0F, 0F);
    Shape13.setTextureSize(64, 64);
    Shape13.mirror = false;
    setRotation(Shape13, 0F, 0F, 0F);
    Shape14 = new ModelRenderer(this, 0, 46);
    Shape14.addBox(-4.5F, -8F, -4.5F, 9, 1, 9);
    Shape14.setRotationPoint(0F, 0F, 0F);
    Shape14.setTextureSize(64, 64);
    Shape14.mirror = false;
    setRotation(Shape14, 0F, 0F, 0F);
    Shape15 = new ModelRenderer(this, 0, 46);
    Shape15.addBox(-3.5F, -10F, -3.5F, 7, 1, 7);
    Shape15.setRotationPoint(0F, 0F, 0F);
    Shape15.setTextureSize(64, 64);
    Shape15.mirror = false;
    setRotation(Shape15, 0F, 0F, 0F);
    Shape16 = new ModelRenderer(this, 5, 50);
    Shape16.addBox(-3F, -10.5F, -3F, 6, 1, 6);
    Shape16.setRotationPoint(0F, 0F, 0F);
    Shape16.setTextureSize(64, 64);
    Shape16.mirror = false;
    setRotation(Shape16, 0F, 0F, 0F);
    Shape17 = new ModelRenderer(this, 50, 48);
    Shape17.addBox(-1F, -8.5F, 3F, 2, 1, 5);
    Shape17.setRotationPoint(0F, 0F, 0F);
    Shape17.setTextureSize(64, 64);
    Shape17.mirror = false;
    setRotation(Shape17, 1.047198F, 3.141593F, 0F);
    Shape18 = new ModelRenderer(this, 50, 48);
    Shape18.addBox(-1F, -8.5F, 3F, 2, 1, 5);
    Shape18.setRotationPoint(0F, 0F, 0F);
    Shape18.setTextureSize(64, 64);
    Shape18.mirror = false;
    setRotation(Shape18, 1.047198F, 0F, 0F);
    Shape19 = new ModelRenderer(this, 46, 51);
    Shape19.addBox(-1F, -11.5F, -3.5F, 2, 1, 7);
    Shape19.setRotationPoint(0F, 0F, 0F);
    Shape19.setTextureSize(64, 64);
    Shape19.mirror = false;
    setRotation(Shape19, 0F, 0F, 0F);
    Shape20 = new ModelRenderer(this, 40, 48);
    Shape20.addBox(-1F, -5F, -5.5F, 2, 3, 1);
    Shape20.setRotationPoint(0F, 0F, 0F);
    Shape20.setTextureSize(64, 64);
    Shape20.mirror = false;
    setRotation(Shape20, 0F, 0F, 0F);
    Shape21 = new ModelRenderer(this, 0, 45);
    Shape21.addBox(-4F, -9F, -4F, 8, 1, 8);
    Shape21.setRotationPoint(0F, 0F, 0F);
    Shape21.setTextureSize(64, 64);
    Shape21.mirror = false;
    setRotation(Shape21, 0F, 0F, 0F);
    Shape6 = new ModelRenderer(this, 36, 37);
    Shape6.addBox(0.5F, -7F, -5F, 4, 7, 1);
    Shape6.setRotationPoint(0F, 0F, 0F);
    Shape6.setTextureSize(64, 64);
    Shape6.mirror = true;
    setRotation(Shape6, 0F, 3.141593F, 0F);


    bipedHead.addChild(Shape1);
    bipedHead.addChild(Shape2);
    bipedHead.addChild(Shape3);
    bipedHead.addChild(Shape4);
    bipedHead.addChild(Shape5);
    bipedHead.addChild(Shape7);
    bipedHead.addChild(Shape8);
    bipedHead.addChild(Shape9);
    bipedHead.addChild(Shape10);
    bipedHead.addChild(Shape11);
    bipedHead.addChild(Shape12);
    bipedHead.addChild(Shape13);
    bipedHead.addChild(Shape14);
    bipedHead.addChild(Shape15);
    bipedHead.addChild(Shape16);
    bipedHead.addChild(Shape17);
    bipedHead.addChild(Shape18);
    bipedHead.addChild(Shape19);
    bipedHead.addChild(Shape20);
    bipedHead.addChild(Shape21);
    bipedHead.addChild(Shape6);


  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

}
