// Date: 22.10.2014 18:36:33
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package assets.bulbs.models.ModelOfficeLight1;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOfficeLight1 extends ModelBase
{
  //fields
    ModelRenderer Bottom;
    ModelRenderer Side1;
    ModelRenderer Side2;
    ModelRenderer Side3;
    ModelRenderer Side4;
    ModelRenderer Diag1;
    ModelRenderer Diag2;
    ModelRenderer Diag3;
    ModelRenderer Diag4;
    ModelRenderer Light;
    ModelRenderer Alpha;
  
  public ModelOfficeLight1()
  {
    textureWidth = 128;
    textureHeight = 128;

      Bottom = new ModelRenderer(this, -30, 0);
      Bottom.addBox(-15F, 0F, -15F, 30, 0, 30);
      Bottom.setRotationPoint(0F, 24F, 0F);
      Bottom.setTextureSize(128, 128);
      Bottom.mirror = true;
      setRotation(Bottom, 0F, 0F, 0F);
      Side1 = new ModelRenderer(this, 0, 32);
      Side1.addBox(-16F, -32F, -16F, 32, 32, 1);
      Side1.setRotationPoint(0F, 24F, 0F);
      Side1.setTextureSize(128, 128);
      Side1.mirror = true;
      setRotation(Side1, 0F, 0F, 0F);
      Side2 = new ModelRenderer(this, 0, 65);
      Side2.addBox(15F, -32F, -15F, 1, 32, 30);
      Side2.setRotationPoint(0F, 24F, 0F);
      Side2.setTextureSize(128, 128);
      Side2.mirror = true;
      setRotation(Side2, 0F, 0F, 0F);
      Side3 = new ModelRenderer(this, 33, 32);
      Side3.addBox(-16F, -32F, 15F, 32, 32, 1);
      Side3.setRotationPoint(0F, 24F, 0F);
      Side3.setTextureSize(128, 128);
      Side3.mirror = true;
      setRotation(Side3, 0F, 0F, 0F);
      Side4 = new ModelRenderer(this, 0, 65);
      Side4.addBox(-16F, -32F, -15F, 1, 32, 30);
      Side4.setRotationPoint(0F, 24F, 0F);
      Side4.setTextureSize(128, 128);
      Side4.mirror = true;
      setRotation(Side4, 0F, 0F, 0F);
      Diag1 = new ModelRenderer(this, 54, 67);
      Diag1.addBox(-5F, 0F, -15F, 5, 0, 30);
      Diag1.setRotationPoint(-3F, -8F, 0F);
      Diag1.setTextureSize(128, 128);
      Diag1.mirror = true;
      setRotation(Diag1, 0F, 0F, -0.5410521F);
      Diag2 = new ModelRenderer(this, 54, 67);
      Diag2.addBox(0F, 0F, -15F, 5, 0, 30);
      Diag2.setRotationPoint(3F, -8F, 0F);
      Diag2.setTextureSize(128, 128);
      Diag2.mirror = true;
      setRotation(Diag2, 0F, 0F, 0.5410521F);
      Diag3 = new ModelRenderer(this, 54, 98);
      Diag3.addBox(0F, 0F, -15F, 19, 1, 30);
      Diag3.setRotationPoint(-15F, -7F, 0F);
      Diag3.setTextureSize(128, 128);
      Diag3.mirror = true;
      setRotation(Diag3, 0F, 0F, 0.6108652F);
      Diag4 = new ModelRenderer(this, 74, 98);
      Diag4.addBox(-19F, 0F, -15F, 19, 1, 30);
      Diag4.setRotationPoint(15F, -7F, 0F);
      Diag4.setTextureSize(128, 128);
      Diag4.mirror = true;
      setRotation(Diag4, 0F, 0F, -0.6108652F);
      Light = new ModelRenderer(this, 36, 67);
      Light.addBox(-4F, -30F, -15F, 8, 1, 30);
      Light.setRotationPoint(0F, 24F, 0F);
      Light.setTextureSize(128, 128);
      Light.mirror = true;
      setRotation(Light, 0F, 0F, 0F);
      Alpha = new ModelRenderer(this, 38, 98);
      Alpha.addBox(-3F, -32F, -15F, 6, 1, 30);
      Alpha.setRotationPoint(0F, 24F, 0F);
      Alpha.setTextureSize(128, 128);
      Alpha.mirror = true;
      setRotation(Alpha, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Bottom.render(f5);
    Side1.render(f5);
    Side2.render(f5);
    Side3.render(f5);
    Side4.render(f5);
    Diag1.render(f5);
    Diag2.render(f5);
    Diag3.render(f5);
    Diag4.render(f5);
    Light.render(f5);
    Alpha.render(f5);
  }

    public void renderModel(float f5) {
        Bottom.render(f5);
        Side1.render(f5);
        Side2.render(f5);
        Side3.render(f5);
        Side4.render(f5);
        Diag1.render(f5);
        Diag2.render(f5);

    }

    public void renderAlpha(float f5) {
        Light.render(f5);
        Diag3.render(f5);
        Diag4.render(f5);
        Alpha.render(f5);
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