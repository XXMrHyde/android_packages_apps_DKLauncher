/*
 * Copyright (C) 2015 The SlimRoms Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.darkkat.dklauncher.settings;

import android.os.Bundle;
import android.preference.Preference;

import com.darkkat.dklauncher.preference.NumberPickerPreference;
import com.darkkat.dklauncher.R;

import net.margaritov.preference.colorpicker.ColorPickerPreference;

public class DockFragment extends SettingsPreferenceFragment
        implements Preference.OnPreferenceChangeListener {

    private ColorPickerPreference mAllAppsIconColor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.dock_preferences);

        final NumberPickerPreference dockIcons = (NumberPickerPreference)
                findPreference(SettingsProvider.KEY_DOCK_ICONS);

        if (mProfile != null) {
            if (SettingsProvider.getInt(getActivity(),
                    SettingsProvider.KEY_DOCK_ICONS, 0) < 1) {
                SettingsProvider.putInt(getActivity(),
                        SettingsProvider.KEY_DOCK_ICONS, (int) mProfile.numHotseatIcons);
                dockIcons.setDefaultValue((int) mProfile.numHotseatIcons);
            }
        }

        mAllAppsIconColor = (ColorPickerPreference) findPreference(
                SettingsProvider.KEY_DOCK_ALL_APPS_ICON_COLOR);
        int intColor = SettingsProvider.getInt(getActivity(),
                SettingsProvider.KEY_DOCK_ALL_APPS_ICON_COLOR, 0xffffffff);
        mAllAppsIconColor.setNewPreviewColor(intColor);
        String hexColor = String.format("#%08x", (0xffffffff & intColor));
        mAllAppsIconColor.setSummary(hexColor);
        mAllAppsIconColor.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mAllAppsIconColor) {
            String hex = ColorPickerPreference.convertToARGB(
                Integer.valueOf(String.valueOf(newValue)));
            int intHex = ColorPickerPreference.convertToColorInt(hex);
            SettingsProvider.putInt(getActivity(),
                    SettingsProvider.KEY_DOCK_ALL_APPS_ICON_COLOR, intHex);
            preference.setSummary(hex);
        }
        return true;
    }
}
