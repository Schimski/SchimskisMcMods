// Date: 22.10.2014 12:13:08
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package assets.bulbs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGridLightCon2CloseX32 extends ModelBase {
  //fields
    ModelRenderer Bottom;
    ModelRenderer Bottom2;
    ModelRenderer Light;
    ModelRenderer Light2;
    ModelRenderer Cage;
    ModelRenderer Cage2;
    ModelRenderer Alpha;
    ModelRenderer Alpha2;
  
  public ModelGridLightCon2CloseX32()
  {
    textureWidth = 256;
    textureHeight = 192;
    
      Bottom = new ModelRenderer(this, 0, 0);
      Bottom.addBox(-12F, 0F, -12F, 28, 2, 24);
      Bottom.setRotationPoint(0F, 22F, 0F);
      Bottom.setTextureSize(256, 128);
      Bottom.mirror = true;
      setRotation(Bottom, 0F, 0F, 0F);
      Bottom2 = new ModelRenderer(this, 24, 0);
      Bottom2.addBox(-12F, 0F, 12F, 24, 2, 4);
      Bottom2.setRotationPoint(0F, 22F, 0F);
      Bottom2.setTextureSize(256, 128);
      Bottom2.mirror = true;
      setRotation(Bottom2, 0F, 0F, 0F);
      Light = new ModelRenderer(this, 0, 98);
      Light.addBox(-8F, 0F, -8F, 24, 2, 16);
      Light.setRotationPoint(0F, 20F, 0F);
      Light.setTextureSize(256, 128);
      Light.mirror = true;
      setRotation(Light, 0F, 0F, 0F);
      Light2 = new ModelRenderer(this, 16, 98);
      Light2.addBox(-8F, 0F, 8F, 16, 2, 8);
      Light2.setRotationPoint(0F, 20F, 0F);
      Light2.setTextureSize(256, 128);
      Light2.mirror = true;
      setRotation(Light2, 0F, 0F, 0F);
      Cage = new ModelRenderer(this, 138, 51);
      Cage.addBox(-10F, 0F, -10F, 26, 4, 20);
      Cage.setRotationPoint(0F, 18F, 0F);
      Cage.setTextureSize(256, 128);
      Cage.mirror = true;
      setRotation(Cage, 0F, 0F, 0F);
      Cage2 = new ModelRenderer(this, 198, 0);
      Cage2.addBox(-10F, 0F, 10F, 20, 4, 6);
      Cage2.setRotationPoint(0F, 18F, 0F);
      Cage2.setTextureSize(256, 128);
      Cage2.mirror = true;
      setRotation(Cage2, 0F, 0F, 0F);
      Alpha = new ModelRenderer(this, 72, 50);
      Alpha.addBox(-9F, 0F, -9F, 25, 3, 18);
      Alpha.setRotationPoint(0F, 19F, 0F);
      Alpha.setTextureSize(256, 128);
      Alpha.mirror = true;
      setRotation(Alpha, 0F, 0F, 0F);
      Alpha2 = new ModelRenderer(this, 195, 29);
      Alpha2.addBox(-9F, -3F, 9F, 18, 3, 7);
      Alpha2.setRotationPoint(0F, 22F, 0F);
      Alpha2.setTextureSize(256, 128);
      Alpha2.mirror = true;
      setRotation(Alpha2, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Bottom.render(f5);
    Bottom2.render(f5);
    Light.render(f5);
    Light2.render(f5);
    Cage.render(f5);
    Cage2.render(f5);
    Alpha.render(f5);
    Alpha2.render(f5);
  }

    public void renderModel(float f5) {
        Bottom.render(f5);
        Bottom2.render(f5);
        Light.render(f5);
        Light2.render(f5);
        Cage.render(f5);
        Cage2.render(f5);
    }

    public void renderAlpha(float f5) {
        Alpha.render(f5);
        Alpha2.render(f5);
    }

  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}