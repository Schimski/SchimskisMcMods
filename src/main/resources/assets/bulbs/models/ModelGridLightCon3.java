// Date: 14.10.2014 21:01:14
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package net.minecraft.src;

public class ModelmodelLight extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer side1;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer top2;
    ModelRenderer bottom;
    ModelRenderer Shape13;
    ModelRenderer top3;
    ModelRenderer top1;
    ModelRenderer Shape5;
    ModelRenderer bottom2;
    ModelRenderer light2;
    ModelRenderer top4;
    ModelRenderer side2;
    ModelRenderer side3;
  
  public ModelmodelLight()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 0, 30);
      Shape1.addBox(0F, 1F, 0F, 1, 0, 10);
      Shape1.setRotationPoint(7.466667F, 20F, -5F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      side1 = new ModelRenderer(this, 0, 0);
      side1.addBox(0F, 1F, 0F, 1, 2, 0);
      side1.setRotationPoint(-8.466666F, 20F, -5F);
      side1.setTextureSize(64, 64);
      side1.mirror = true;
      setRotation(side1, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 0, 0);
      Shape7.addBox(0F, 1F, 0F, 1, 2, 0);
      Shape7.setRotationPoint(7.533333F, 20F, 5F);
      Shape7.setTextureSize(64, 64);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 0, 0);
      Shape8.addBox(0F, 1F, 0F, 1, 2, 0);
      Shape8.setRotationPoint(7.466667F, 20F, -5F);
      Shape8.setTextureSize(64, 64);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 0, 20);
      Shape9.addBox(0F, 1F, 0F, 16, 1, 8);
      Shape9.setRotationPoint(-8F, 21F, -4F);
      Shape9.setTextureSize(64, 64);
      Shape9.mirror = true;
      setRotation(Shape9, 0F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 0, 0);
      Shape10.addBox(0F, 1F, 0F, 16, 0, 1);
      Shape10.setRotationPoint(-8F, 20F, 2F);
      Shape10.setTextureSize(64, 64);
      Shape10.mirror = true;
      setRotation(Shape10, 0F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 0, 0);
      Shape11.addBox(0F, 1F, 0F, 16, 0, 1);
      Shape11.setRotationPoint(-8F, 20F, -3F);
      Shape11.setTextureSize(64, 64);
      Shape11.mirror = true;
      setRotation(Shape11, 0F, 0F, 0F);
      top2 = new ModelRenderer(this, 0, 30);
      top2.addBox(0F, 1F, 0F, 1, 0, 13);
      top2.setRotationPoint(-3F, 20F, -5F);
      top2.setTextureSize(64, 64);
      top2.mirror = true;
      setRotation(top2, 0F, 0F, 0F);
      bottom = new ModelRenderer(this, 0, 4);
      bottom.addBox(0F, 1F, 0F, 16, 1, 12);
      bottom.setRotationPoint(-8F, 22F, -6F);
      bottom.setTextureSize(64, 64);
      bottom.mirror = true;
      setRotation(bottom, 0F, 0F, 0F);
      Shape13 = new ModelRenderer(this, 0, 0);
      Shape13.addBox(0F, 1F, 0F, 1, 2, 0);
      Shape13.setRotationPoint(2F, 20F, -5F);
      Shape13.setTextureSize(64, 64);
      Shape13.mirror = true;
      setRotation(Shape13, 0F, 0F, 0F);
      top3 = new ModelRenderer(this, 0, 30);
      top3.addBox(0F, 1F, 0F, 1, 0, 13);
      top3.setRotationPoint(2F, 20F, -5F);
      top3.setTextureSize(64, 64);
      top3.mirror = true;
      setRotation(top3, 0F, 0F, 0F);
      top1 = new ModelRenderer(this, 0, 30);
      top1.addBox(0F, 1F, 0F, 1, 0, 10);
      top1.setRotationPoint(-8.466666F, 20F, -5F);
      top1.setTextureSize(64, 64);
      top1.mirror = true;
      setRotation(top1, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 0, 0);
      Shape5.addBox(0F, 1F, 0F, 1, 2, 0);
      Shape5.setRotationPoint(-3F, 20F, -5F);
      Shape5.setTextureSize(64, 64);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      bottom2 = new ModelRenderer(this, 0, 0);
      bottom2.addBox(0F, 1F, 0F, 12, 1, 2);
      bottom2.setRotationPoint(-6F, 22F, 6F);
      bottom2.setTextureSize(64, 64);
      bottom2.mirror = true;
      setRotation(bottom2, 0F, 0F, 0F);
      light2 = new ModelRenderer(this, 8, 20);
      light2.addBox(0F, 1F, 0F, 8, 1, 4);
      light2.setRotationPoint(-4F, 21F, 4F);
      light2.setTextureSize(64, 64);
      light2.mirror = true;
      setRotation(light2, 0F, 0F, 0F);
      top4 = new ModelRenderer(this, 0, 0);
      top4.addBox(0F, 1F, 0F, 10, 0, 1);
      top4.setRotationPoint(-5F, 20F, 7.466667F);
      top4.setTextureSize(64, 64);
      top4.mirror = true;
      setRotation(top4, 0F, 0F, 0F);
      side2 = new ModelRenderer(this, 0, 0);
      side2.addBox(0F, 1F, 0F, 0, 2, 1);
      side2.setRotationPoint(-5F, 20F, 7.466667F);
      side2.setTextureSize(64, 64);
      side2.mirror = true;
      setRotation(side2, 0F, 0F, 0F);
      side3 = new ModelRenderer(this, 0, 0);
      side3.addBox(0F, 1F, 0F, 0, 2, 1);
      side3.setRotationPoint(5F, 20F, 7.466667F);
      side3.setTextureSize(64, 64);
      side3.mirror = true;
      setRotation(side3, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Shape1.render(f5);
    side1.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
    Shape10.render(f5);
    Shape11.render(f5);
    top2.render(f5);
    bottom.render(f5);
    Shape13.render(f5);
    top3.render(f5);
    top1.render(f5);
    Shape5.render(f5);
    bottom2.render(f5);
    light2.render(f5);
    top4.render(f5);
    side2.render(f5);
    side3.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5);
  }

}
