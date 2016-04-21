package org.mamasdelrio.android.widget;

import android.widget.DatePicker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mamasdelrio.android.BuildConfig;
import org.mamasdelrio.android.DoAlarmActivity;
import org.mamasdelrio.android.logic.DatePickerHelper;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.android.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link DeathView}.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class DeathViewTest {
  DeathView view;

  @Before
  public void before() {
    view = new DeathView(RuntimeEnvironment.application);
  }

  @Test
  public void uiElementsInitialized() {
    assertThat(view.deathDate)
        .isEnabled()
        .isVisible();
    assertThat(view.probableCause)
        .isEnabled()
        .isVisible();
  }

  @Test
  public void addValuesToMapCorrect() {
    String probableCauseKey = "probableCause";
    String dateKey = "date";
    String targetDate = "2015-02-14";
    String targetCause = "something bad";
    view.probableCause.setText(targetCause);
    DatePickerHelper dphMock = mock(DatePickerHelper.class);
    when(dphMock.getFriendlyString(any(DatePicker.class)))
        .thenReturn(targetDate);
    Map<String, Object> map = new HashMap<>();
    view.addValuesToMap(map, dphMock, probableCauseKey, dateKey);
    assertThat(map).contains(
        entry(probableCauseKey, targetCause),
        entry(dateKey, targetDate));
  }
}
