package ml.empee.itembuilder;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;

/**
 * Item builder for {@link Material#POTION}, {@link Material#LINGERING_POTION} and {@link Material#SPLASH_POTION} only
 *
 * @author Mr. EmPee
 */
public class PotionBuilder extends BaseItemBuilder<PotionBuilder> {

  private static final EnumSet<Material> POTIONS = EnumSet.of(
      Material.POTION, Material.SPLASH_POTION, Material.LINGERING_POTION
  );

  PotionBuilder(@NotNull ItemStack itemStack) {
    super(itemStack);

    if (!POTIONS.contains(itemStack.getType())) {
      throw new RuntimeException("PotionBuilder requires the material to be a POTION!");
    }
  }

  /**
   * Sets the color of the potion
   *
   * @param color the color to set
   * @return {@link PotionBuilder}
   */
  @NotNull
  @Contract("_ -> this")
  public PotionBuilder color(@NotNull final Color color) {
    final PotionMeta meta = (PotionMeta) getMeta();

    meta.setColor(color);
    setMeta(meta);
    return this;
  }

  /**
   * Sets the base data of the potion
   *
   * @param data the data to set
   * @return {@link PotionBuilder}
   */
  @NotNull
  @Contract("_ -> this")
  public PotionBuilder data(@NotNull final PotionData data) {
    final PotionMeta meta = (PotionMeta) getMeta();

    meta.setBasePotionData(data);
    setMeta(meta);
    return this;
  }


  /**
   * Sets a custom effect for the potion
   *
   * @param effect the effect to set
   * @return {@link PotionBuilder}
   */
  @NotNull
  @Contract("_ , _ -> this")
  public PotionBuilder customEffect(@NotNull final PotionEffect effect, boolean overwrite) {
    final PotionMeta meta = (PotionMeta) getMeta();

    meta.addCustomEffect(effect, overwrite);
    setMeta(meta);
    return this;
  }

}
