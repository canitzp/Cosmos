package de.canitzp.cosmos.machine.spaceeye;

import de.canitzp.cosmos.Cosmos;
import de.canitzp.cosmos.spaceobjects.SpacePosition;
import de.canitzp.cosmos.spaceobjects.sat.SatTypes;
import de.canitzp.cosmos.spaceobjects.sat.Satellite;
import de.canitzp.cosmos.data.SpaceData;
import de.canitzp.cosmos.spaceobjects.space.Galaxy;
import de.canitzp.cosmos.spaceobjects.space.Planet;
import de.canitzp.ctpcore.inventory.FakeContainer;
import de.canitzp.ctpcore.inventory.GuiContainerBase;
import de.canitzp.ctpcore.tile.FakeTileEntity;
import de.canitzp.ctpcore.util.GuiUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.GuiScrollingList;
import org.lwjgl.input.Mouse;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author canitzp
 */
public class GuiSpaceEye extends GuiContainerBase<FakeTileEntity> {

    private static final ResourceLocation guiLoc = new ResourceLocation(Cosmos.MODID, "textures/gui/gui_space_eye.png");

    private List<IElement> elements = new ArrayList<>();
    private int activeElement = -1;
    private Info scrollInfo;

    public GuiSpaceEye(EntityPlayer player, int x,int y, int z) {
        super(new FakeContainer<>(player, x, y, z));
        this.xSize = 256;
        this.ySize = 166;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.activeElement = -1;
        this.elements.clear();
        List<Satellite> satellites = SpaceData.satellites;
        for (int i = 0; i < satellites.size(); i++) {
            elements.add(new SatelliteElement(satellites.get(i), this.guiLeft + 7, this.guiTop + i * 7 + 7));
        }
        scrollInfo  = new Info(this);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        clearColor();
        drawBackgroundLocation(guiLoc);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        if(scrollInfo != null)
            scrollInfo.drawScreen(mouseX, mouseY, partialTicks);
        if(this.activeElement != -1){
            IElement element = this.elements.get(this.activeElement);
            int y = 0;
            for(String line : element.getDescription()){
                GlStateManager.pushMatrix();
                GlStateManager.translate(this.guiLeft+120, this.guiTop+8*y+7, 0);
                GlStateManager.scale(0.85F, 0.8F, 1);
                this.mc.fontRenderer.drawString(line, 0, 0, 0xFFFFFF);
                GlStateManager.popMatrix();
                y++;
            }
        }
    }

    @Override
    public void handleMouseInput() throws IOException {
        int mouseX = Mouse.getEventX() * this.width / this.mc.displayWidth;
        int mouseY = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
        super.handleMouseInput();
        if(scrollInfo != null)
            scrollInfo.handleMouseInput(mouseX, mouseY);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if(this.activeElement != -1){
            if(GuiUtil.isMouseInRange(mouseX, mouseY, this.guiLeft, this.guiTop, 146, 96, 26, 18)){
                //TODO open confirm dialog
                IElement element = this.elements.get(this.activeElement);
                if(element instanceof SatelliteElement){
                    SpaceData.satellites.remove(((SatelliteElement) element).satellite);
                }
                initGui();
            } else if(!GuiUtil.isMouseInRange(mouseX, mouseY, this.guiLeft,this.guiTop, 7, 7, 102, 150)){
                this.activeElement = -1;
            }
        }
        if(GuiUtil.isMouseInRange(mouseX, mouseY, this.guiLeft, this.guiTop, 119, 96, 26, 18)){
            SpaceData.satellites.add(new Satellite(String.valueOf(mouseX + mouseY), new SpacePosition(Planet.EARTH), SatTypes.COMMUNICATION));
            initGui();
        }
    }

    public abstract class IElement{
        public int x, y;
        abstract @Nonnull String getFirstLine();
        abstract @Nullable String getSecondLine();
        abstract @Nonnull List<String> getDescription();
        public IElement(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public class SatelliteElement extends IElement{

        public Satellite satellite;

        public SatelliteElement(Satellite satellite, int x, int y){
            super(x, y);
            this.satellite = satellite;
        }

        @Nonnull
        @Override
        public String getFirstLine(){
            return TextFormatting.BLACK + this.satellite.getName();
        }

        @Nullable
        @Override
        public String getSecondLine() {
            return TextFormatting.DARK_GRAY + this.satellite.getPosition().getClosestLocationString("Location: ");
        }

        @Nonnull
        @Override
        public List<String> getDescription() {
            return this.satellite.getProperties(new ArrayList<>());
        }
    }

    public class Info extends GuiScrollingList{

        private GuiSpaceEye gui;

        public Info(GuiSpaceEye gui) {
            super(gui.mc, 108, 150, gui.guiTop + 7, gui.guiTop + 157, gui.guiLeft + 7, 15, gui.xSize, gui.ySize);
            this.gui = gui;
        }

        @Override
        protected int getSize() {
            return this.gui.elements.size();
        }

        @Override
        protected void elementClicked(int index, boolean doubleClick) {
            this.gui.activeElement = index;
        }

        @Override
        protected boolean isSelected(int index) {
            return false;
        }

        @Override
        protected void drawBackground() {

        }

        @Override
        protected void drawSlot(int slotIdx, int entryRight, int slotTop, int slotBuffer, Tessellator tess) {
            this.gui.bindTexture(guiLoc);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            IElement element = this.gui.elements.get(slotIdx);
            this.gui.drawTexturedModalRect(this.gui.guiLeft + 7, slotTop - 2, 0, slotIdx == this.gui.activeElement ? 180 : 166, 108, 15);
            GlStateManager.pushMatrix();
            GlStateManager.translate(this.gui.guiLeft + 8, slotTop - 1, 1);
            GlStateManager.scale(0.95F, 0.85F, 1.0F);
            this.gui.mc.fontRenderer.drawString(element.getFirstLine(), 0, 0, 0);
            if(element.getSecondLine() != null){
                GlStateManager.scale(0.8F, 0.8F, 1.0F);
                this.gui.mc.fontRenderer.drawString(element.getSecondLine(), 0, 11, 0);
            }
            GlStateManager.popMatrix();
        }

    }
}
