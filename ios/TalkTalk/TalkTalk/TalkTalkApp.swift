//
//  TalkTalkApp.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 26/08/2024.
//

import SwiftUI

@main
struct TalkTalkApp: App {
    @AppStorage(UserDefaultsKeys.DARK_THEME) private var _darkTheme: Bool = false
    
    var body: some Scene {
        WindowGroup {
            NavigationStack {
                NewTalkView()
                    .preferredColorScheme(_darkTheme ? .dark : .light)
            }.tint(_darkTheme ? .white : .black)
        }
    }
}
