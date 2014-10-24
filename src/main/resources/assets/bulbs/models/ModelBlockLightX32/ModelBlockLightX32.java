// Date: 24.10.2014 14:50:28
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package assets.bulbs.models.ModelBlockLightX32;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBlockLightX32 extends ModelBase
{
  //fields
    ModelRenderer Light;
    ModelRenderer Alpha;
  
  public ModelBlockLightX32()
  {
    textureWidth = 192;
    textureHeight = 192;
    
      Light = new ModelRenderer(this, 0, 0);
      Light.addBox(-15F, -31F, -15F, 30, 30, 30);
      Light.setRotationPoint(0F, 24F, 0F);
      Light.setTextureSize(128, 128);
      Light.mirror = true;
      setRotation(Light, 0F, 0F, 0F);
      Alpha = new ModelRenderer(this, 0, 64);
      Alpha.addBox(-17F, -33F, -17F, 34, 34, 34);
      Alpha.setRotationPoint(0F, 24F, 0F);
      Alpha.setTextureSize(128, 128);
      Alpha.mirror = true;
      setRotation(Alpha, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Light.render(f5);
    Alpha.render(f5);
  }

    public void renderModel(float f5) {
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
