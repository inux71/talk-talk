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
        Text("Settings view")
    }
}

#Preview {
    SettingsView()
}
