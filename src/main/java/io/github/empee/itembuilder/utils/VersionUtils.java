package io.github.empee.itembuilder.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class VersionUtils {

  /**
   * The middle number of the server version - for example, if the server version is 1.15.2, this will be 15
   */
  public static final int MID_VERSION = getMidVersion();

  private static int getMidVersion() {
    Pattern pattern = Pattern.compile("1\\.([0-9]+)");
    Matcher matcher = pattern.matcher(Bukkit.getBukkitVersion());
    matcher.find();

    return Integer.parseInt(matcher.group(1));
  }

}
