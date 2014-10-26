// Date: 21.10.2014 06:23:40
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package assets.bulbs.models.ModelThinLightX32;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelThinLightCon1X32 extends ModelBase
{
  //fields
    ModelRenderer Bottom;
    ModelRenderer Alpha;
    ModelRenderer Light;
  
  public ModelThinLightCon1X32()
  {
    textureWidth = 128;
    textureHeight = 64;

      Bottom = new ModelRenderer(this, 0, 0);
      Bottom.addBox(-16F, -1F, -4F, 32, 1, 8);
      Bottom.setRotationPoint(0F, 24F, 0F);
      Bottom.setTextureSize(128, 64);
      Bottom.mirror = true;
      setRotation(Bottom, 0F, 0F, 0F);
      Alpha = new ModelRenderer(this, 0, 26);
      Alpha.addBox(-15F, -2F, -3F, 31, 1, 6);
      Alpha.setRotationPoint(0F, 24F, 0F);
      Alpha.setTextureSize(128, 64);
      Alpha.mirror = true;
      setRotation(Alpha, 0F, 0F, 0F);
      Light = new ModelRenderer(this, 0, 21);
      Light.addBox(-14F, -1.5F, -2F, 30, 1, 4);
      Light.setRotationPoint(0F, 24F, 0F);
      Light.setTextureSize(128, 64);
      Light.mirror = true;
      setRotation(Light, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Bottom.render(f5);
    Alpha.render(f5);
    Light.render(f5);
  }

    public void renderModel(float f5) {
        Bottom.render(f5);
        Light.render(f5);
    }

    public void renderAlpha(float f5) {
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