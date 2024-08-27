//
//  TabsView.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 27/08/2024.
//

import SwiftUI

struct TabsView: View {
    var body: some View {
        TabView {
            TalkListView()
                .tabItem {
                    Label(
                        "talk-list-tab",
                        systemImage: "ellipsis.message.fill"
                    )
                }
            
            ProfileView()
                .tabItem {
                    Label(
                        "profile-tab",
                        systemImage: "person.crop.circle"
                    )
                }
            
            SettingsView()
                .tabItem {
                    Label(
                        "settings-tab",
                        systemImage: "gear"
                    )
                }
        }
    }
}

#Preview {
    TabsView()
}
