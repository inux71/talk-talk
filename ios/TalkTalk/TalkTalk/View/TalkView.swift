//
//  TalkView.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 05/09/2024.
//

import SwiftUI

struct TalkView: View {
    @StateObject private var _viewModel: TalkViewModel = TalkViewModel()
    
    let id: Int
    
    var body: some View {
        ScrollView {
            TextMessage(
                message: "Hi!",
                date: Date(),
                reversed: false
            )
            
            TextMessage(
                message: "What's up?", 
                date: Date(),
                reversed: false
            )
    
            TextMessage(
                message: "Hello!",
                date: Date(),
                reversed: true
            )
        }.navigationTitle(_viewModel.talkName)
            .toolbar {
                ToolbarItemGroup(placement: .topBarTrailing) {
                    Button(
                        "Call",
                        systemImage: "phone.fill",
                        action: {}
                    )
                    
                    Button(
                        "Video call",
                        systemImage: "video.fill",
                        action: {}
                    )
                }
                
                ToolbarItemGroup(placement: .bottomBar) {
                    HStack {
                        Menu {
                            Button(action: {}) {
                                Label(
                                    "Image",
                                    systemImage: "photo"
                                )
                            }
                            
                            Button(action: {}) {
                                Label(
                                    "Camera",
                                    systemImage: "camera.fill"
                                )
                            }
                            
                            Button(action: {}) {
                                Label(
                                    "File",
                                    systemImage: "doc.fill.badge.plus"
                                )
                            }
                            
                            Button(action: {}) {
                                Label(
                                    "Location",
                                    systemImage: "location.fill"
                                )
                            }
                        } label: {
                            Label(
                                "Add",
                                systemImage: "plus"
                            ).labelStyle(.iconOnly)
                        }.labelStyle(.titleAndIcon)
                        
                        TextField(
                            "Message",
                            text: $_viewModel.message,
                            prompt: Text("Message")
                        ).textFieldStyle(.roundedBorder)
                            .frame(maxWidth: .infinity)
                        
                        if _viewModel.message.isBlank {
                            Button(
                                "Microphone",
                                systemImage: "mic.fill",
                                action: {}
                            )
                        } else {
                            Button(
                                "Send",
                                systemImage: "paperplane.fill",
                                action: {}
                            )
                        }
                    }
                }
            }
    }
}

#Preview {
    TalkView(id: 1)
}
