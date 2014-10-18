// Date: 18.10.2014 22:22:12
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package assets.bulbs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelThinLightCon3 extends ModelBase
{
  //fields
    ModelRenderer ThinLightBottom;
    ModelRenderer ThinLightBottom2;
    ModelRenderer ThinLightLight;
    ModelRenderer ThinLightLight2;
  
  public ModelThinLightCon3()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      ThinLightBottom = new ModelRenderer(this, 0, 0);
      ThinLightBottom.addBox(-8F, -1F, -2F, 16, 1, 4);
      ThinLightBottom.setRotationPoint(0F, 24F, 0F);
      ThinLightBottom.setTextureSize(64, 32);
      ThinLightBottom.mirror = true;
      setRotation(ThinLightBottom, 0F, 0F, 0F);
      ThinLightBottom2 = new ModelRenderer(this, 0, 14);
      ThinLightBottom2.addBox(-2F, -1F, -8F, 4, 1, 6);
      ThinLightBottom2.setRotationPoint(0F, 24F, 0F);
      ThinLightBottom2.setTextureSize(64, 32);
      ThinLightBottom2.mirror = true;
      setRotation(ThinLightBottom2, 0F, 0F, 0F);
      ThinLightLight = new ModelRenderer(this, 0, 8);
      ThinLightLight.addBox(-8.5F, -1.466667F, -1.466667F, 17, 1, 3);
      ThinLightLight.setRotationPoint(0F, 24F, 0F);
      ThinLightLight.setTextureSize(64, 32);
      ThinLightLight.mirror = true;
      setRotation(ThinLightLight, 0F, 0F, 0F);
      ThinLightLight2 = new ModelRenderer(this, 40, 7);
      ThinLightLight2.addBox(-1.5F, -1.466667F, -8.466666F, 3, 1, 7);
      ThinLightLight2.setRotationPoint(0F, 24F, 0F);
      ThinLightLight2.setTextureSize(64, 32);
      ThinLightLight2.mirror = true;
      setRotation(ThinLightLight2, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    ThinLightBottom.render(f5);
    ThinLightBottom2.render(f5);
    ThinLightLight.render(f5);
    ThinLightLight2.render(f5);
  }

    public void renderModel(float f5) {
        ThinLightBottom.render(f5);
        ThinLightBottom2.render(f5);
        ThinLightLight.render(f5);
        ThinLightLight2.render(f5);
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
