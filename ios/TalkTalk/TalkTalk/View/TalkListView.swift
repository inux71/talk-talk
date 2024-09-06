//
//  TalkListView.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 27/08/2024.
//

import SwiftUI

struct TalkListView: View {
    @StateObject private var _viewModel: TalkListViewModel = TalkListViewModel()
    
    var body: some View {
        List {
            Picker("Talk type", selection: $_viewModel.selectedTalkType) {
                ForEach(TalkType.allCases, id: \.self) { talkType in
                    Text(talkType.rawValue)
                }
            }.pickerStyle(.segmented)
            
            ForEach(1...5, id: \.self) {
                TalkHeader(
                    talkName: "Kacper Grabiec",
                    lastMessageSenderName: "You",
                    lastMessage: "Ok",
                    lastMessageDate: Date(),
                    muted: true,
                    blocked: true,
                    unread: true,
                    talkImageUrl: URL(string: "https://scontent-waw2-2.xx.fbcdn.net/v/t39.30808-1/406051853_3340379186261131_8985740721870838088_n.jpg?stp=cp6_dst-jpg_s480x480&_nc_cat=109&ccb=1-7&_nc_sid=0ecb9b&_nc_ohc=v97YlMmqj64Q7kNvgFG1f1S&_nc_ht=scontent-waw2-2.xx&oh=00_AYCWlbfSDXKM7rpbk4DPKpD6ncVaWqbxgnetuoShozKJ5w&oe=66CCDFBA")!
                ).badge($0)
                    .swipeActions {
                        Button(
                            role: .destructive,
                            action: {}
                        ) {
                            Label(
                                "Delete",
                                systemImage: "trash.fill"
                            )
                        }
                        
                        Menu(
                            "Options",
                            systemImage: "ellipsis"
                        ) {
                            Button(action: {}) {
                                Label(
                                    "Mute",
                                    systemImage: "bell.slash.fill"
                                )
                            }
                            
                            Button(action: {}) {
                                Label(
                                    "Block",
                                    systemImage: "nosign"
                                )
                            }
                        }.labelStyle(.titleAndIcon)
                    }
            }.onDelete { indexSet in
                // To do
            }
        }.navigationTitle("Talks")
            .toolbar {
                ToolbarItemGroup {
                    Button(
                        "Profile",
                        systemImage: "person.crop.circle",
                        action: {}
                    )
                    
                    Button(
                        "Settings",
                        systemImage: "gear",
                        action: {}
                    )
                    
                    Button(
                        "New talk",
                        systemImage: "plus.circle",
                        action: {}
                    )
                }
            }.searchable(
                text: $_viewModel.searchQuery,
                prompt: "Search"
            )
    }
}

#Preview {
    TalkListView()
}
