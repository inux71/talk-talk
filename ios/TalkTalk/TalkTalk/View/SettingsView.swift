//
//  SettingsView.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 27/08/2024.
//

import SwiftUI

struct SettingsView: View {
    @StateObject private var _viewModel: SettingsViewModel = SettingsViewModel()
    
    var body: some View {
        List {
            Section {
                Toggle(
                    "dark-theme",
                    isOn: $_viewModel.isDarkTheme
                ).onChange(of: _viewModel.isDarkTheme) {
                    _viewModel.saveTheme()
                }
            } header: {
                Text("Apperance")
            }
            
            Section {
                Button(
                    "clear-data",
                    role: .destructive,
                    action: {
                        _viewModel.clearUserDefaults()
                    }
                )
            } header: {
                Text("Local data")
            }
        }.navigationTitle("settings-title")
    }
}

#Preview {
    SettingsView()
}
