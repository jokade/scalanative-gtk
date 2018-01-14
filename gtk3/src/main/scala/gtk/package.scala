// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).

import scalanative.native._

package object gtk {
  /* gtkwindow.h */
  type GtkWindowType = Int
  object GtkWindowType {
    val TOPLEVEL: GtkWindowType = 0
    val POPUP: GtkWindowType = 1
  }


  /* gtkenums.h */
  type GtkAlign = Int
  object GtkAlign {
    val FILL = 0
    val START = 1
    val END = 2
    val CENTER = 3
    val BASELINE = 4
  }

  type GtkArrowType = Int
  object GtkArrowType {
    val UP = 0
    val DOWN = 1
    val LEFT = 2
    val RIGHT = 3
    val NONE = 4
  }

  type GtkBaselinePosition = Int
  object GtkBaselinePosition {
    val TOP = 0
    val CENTER = 1
    val BOTTOM = 2
  }

  type GtkDeleteType = Int
  object GtkDeleteType {
    val CHARS = 0
    val WORD_ENDS = 1
    val WORDS = 2
    val DISPLAY_LINES = 3
    val DISPLAY_LINE_ENDS = 4
    val PARAGRAPH_ENDS = 5
    val PARAGRAPHS = 6
    val WHITESPACE = 7
  }

  type GtkDirectionType = Int
  object GtkDirectionType {
    val TAB_FORWARD = 0
    val TAB_BACKWARD = 1
    val UP = 2
    val DOWN = 3
    val LEFT = 4
    val RIGHT = 5
  }

  type GtkIconSize = Int
  object GtkIconSize {
    val INVALID = 0
    val MENU = 1
    val SMALL_TOOLBAR = 2
    val LARGE_TOOLBAR = 3
    val BUTTON = 4
    val DND = 5
    val DIALOG = 6
  }

  type GtkSensitivityType = Int
  object GtkSensitivityType {
    val AUTO = 0
    val ON = 1
    val OFF = 2
  }

  type GtkTextDirection = Int
  object GtkTextDirection {
    val NONE = 0
    val LTR = 1
    val RTL = 2
  }

  type GtkJustification = Int
  object GtkJustification {
    val LEFT = 0
    val RIGHT = 1
    val CENTER = 2
    val FILL = 3
  }

  type GtkMenuDirectionType = Int
  object GtkMenuDirectionType {
    val PARENT = 0
    val CHILD = 1
    val NEXT = 2
    val PREV = 3
  }

  type GtkMessageType = Int
  object GtkMessageType {
    val INFO = 0
    val WARNING = 1
    val QUESTION = 2
    val ERROR = 3
    val OTHER = 4
  }

  type GtkMovementStep = Int
  object GtkMovementStep {
    val LOGICAL_POSITIONS = 0
    val VISUAL_POSITIONS = 1
    val WORDS = 2
    val DISPLAY_LINES = 3
    val DISPLAY_LINE_ENDS = 4
    val PARAGRAPHS = 5
    val PARAGRAPH_ENDS = 6
    val PAGES = 7
    val BUFFER_ENDS = 8
    val HORIZONTAL_PAGES = 9
  }

  type GtkScrollStep = Int
  object GtkScrollStep {
    val STEPS = 0
    val PAGES = 1
    val ENDS = 2
    val HORIZONTAL_STEPS = 3
    val HORIZONTAL_PAGES = 4
    val HORIZONTAL_ENDS = 5
  }

  type GtkOrientation = Int
  object GtkOrientation {
    val HORIZONTAL = 0
    val VERTICAL = 1
  }

  type GtkPackType = Int
  object GtkPackType {
    val START = 0
    val END = 1
  }

  type GtkPositionType = Int
  object GtkPositionType {
    val LEFT = 0
    val RIGHT = 1
    val TOP = 2
    val BOTTOM = 3
  }

  type GtkReliefStyle = Int
  object GtkReliefStyle {
    val NORMAL = 0
    val HALF = 1
    val NONE = 2
  }

  type GtkScrollType = Int
  object GtkScrollType {
    val NONE = 0
    val JUMP = 1
    val STEP_BACKWARD = 2
    val STEP_FORWARD = 3
    val PAGE_BACKWARD = 4
    val PAGE_FORWARD = 5
    val STEP_UP = 6
    val STEP_DOWN = 7
    val PAGE_UP = 8
    val PAGE_DOWN = 9
    val STEP_LEFT = 10
    val STEP_RIGHT = 11
    val PAGE_LEFT = 12
    val PAGE_RIGHT = 13
    val START = 14
    val END = 15
  }

  type GtkSelectionMode = Int
  object GtkSelectionMode {
    val NONE = 0
    val SINGLE = 1
    val BROWSE = 2
    val MULTIPLE = 3
  }

  type GtkShadowType = Int
  object GtkShadowType {
    val NONE = 0
    val IN = 1
    val OUT = 2
    val ETCHED_IN = 3
    val ETCHED_OUT = 4
  }

  type GtkStateType = Int
  object GtkStateType {
    val NORMAL = 0
    val ACTIVE = 1
    val PRELIGHT = 2
    val SELECTED = 3
    val INSENSITIVE = 4
    val INCONSISTENT = 5
    val FOCUSED = 6
  }

  type GtkToolbarStyle = Int
  object GtkToolbarStyle {
    val ICONS = 0
    val TEXT = 1
    val BOTH = 2
    val BOTH_HORIZ = 3
  }

  type GtkWrapMode = Int
  object GtkWrapMode {
    val NONE = 0
    val CHAR = 1
    val WORD = 2
    val WORD_CHAR = 3
  }

//  type GtkStockId = CString
//  object GtkStockId {
//
//  }
}
