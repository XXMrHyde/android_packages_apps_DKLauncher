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

import com.darkkat.dklauncher.preference.DoubleNumberPickerPreference;
import com.darkkat.dklauncher.R;

public class DrawerFragment extends SettingsPreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.drawer_preferences);

        final DoubleNumberPickerPreference drawerGrid = (DoubleNumberPickerPreference)
                findPreference(SettingsProvider.KEY_DRAWER_GRID);

        if (mProfile != null) {
            if (SettingsProvider.getCellCountX(getActivity(),
                    SettingsProvider.KEY_DRAWER_GRID, 0) < 1) {
                SettingsProvider.putCellCountX(getActivity(),
                        SettingsProvider.KEY_DRAWER_GRID, mProfile.allAppsNumCols);
                drawerGrid.setDefault2(mProfile.allAppsNumCols);
            }
            if (SettingsProvider.getCellCountY(getActivity(),
                    SettingsProvider.KEY_DRAWER_GRID, 0) < 1) {
                SettingsProvider.putCellCountY(getActivity(),
                        SettingsProvider.KEY_DRAWER_GRID, mProfile.allAppsNumRows);
                drawerGrid.setDefault1(mProfile.allAppsNumRows);
            }
        }
    }
}

