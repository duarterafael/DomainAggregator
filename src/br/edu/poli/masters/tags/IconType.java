package br.edu.poli.masters.tags;

public enum IconType {
	  HAS_NOT(""),
	  HELP("help"),
	  IDEA("idea"),
	  YES("yes"),
	  MESSAGEBOX_WARNING("messagebox_warning"),
	  STOP_SIGN("stop_sign"),
	  CLOSED("closed"),
	  INFO("info"),
	  BUTTON_OK("button_ok"),
	  BUTTON_CANCEL("button_cancel"),
	  FULL_1("full_1"),
	  FULL_2("full_2"),
	  FULL_3("full_3"),
	  FULL_4("full_4"),
	  FULL_5("full_5"),
	  FULL_6("full_6"),
	  FULL_7("full_7"),
	  FULL_8("full_8"),
	  FULL_9("full_9"),
	  FULL_0("full_0"),
	  STOP("stop"),
	  PREPARE("prepare"),
	  GO("go"),
	  BACK("back"),
	  FORWARD("forward"),
	  UP("up"),
	  DOWN("down"),
	  ATTACH("attach"),
	  KSMILETRIS("ksmiletris"),
	  SMILEY_NEUTRAL("smiley_neutral"),
	  SMILEY_OH("smiley_oh"),
	  SMILEY_ANGRY("smiley_angry"),
	  SMILY_BAD("smily_bad"),
	  CLANBOMBER("clanbomber"),
	  DESKTOP_NEW("desktop_new"),
	  GOHOME("gohome"),
	  FOLDER("folder"),
	  KORN("korn"),
	  MAIL("Mail"),
	  KMAIL("kmail"),
	  LIST("list"),
	  EDIT("edit"),
	  KADDRESSBOOK("kaddressbook"),
	  KNOTIFY("knotify"),
	  PASSWORD("password"),
	  PENCIL("pencil"),
	  WIZARD("wizard"),
	  XMAG("xmag"),
	  BELL("bell"),
	  BOOKMARK("bookmark"),
	  PENGUIN("penguin"),
	  LICQ("licq"),
	  FREEMIND_BUTTERFLY("freemind_butterfly"),
	  BROKEN_LINE("broken_line"),
	  CALENDAR("calendar"),
	  CLOCK("clock"),
	  HOURGLASS("hourglass"),
	  LAUNCH("launch"),
	  FLAG_BLACK("flag_black"),
	  FLAG_BLUE("flag_blue"),
	  FLAG_GREEN("flag_green"),
	  FLAG_ORANGE("flag_orange"),
	  FLAG_PINK("flag_pink"),
	  FLAG("flag"),
	  FLAG_YELLOW("flag_yellow"),
	  FAMILY("family"),
	  FEMALE1("female1"),
	  FEMALE2("female2"),
	  MALE1("male1"),
	  MALE2("male2"),
	  FEMA("fema"),
	  GROUP("group");


	  private String text;

	  IconType(String text) {
	    this.text = text;
	  }

	  public String getText() {
	    return this.text;
	  }

	  public static IconType getByText(String text) {
	    if (text != null) {
	      for (IconType type : IconType.values()) {
	        if (text.equalsIgnoreCase(type.text)) {
	          return type;
	        }
	      }
	    }
	    return null;
	  }
}
