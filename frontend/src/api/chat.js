const BASE = '';

export async function getChatRoom(roomId) {
  const res = await fetch(`${BASE}/api/chat-rooms/${roomId}`);
  if (!res.ok) throw new Error(await res.text());
  return res.json();
}

export async function getMessages(chatRoomId) {
  const res = await fetch(`${BASE}/api/chat-rooms/${chatRoomId}/messages`);
  if (!res.ok) throw new Error(await res.text());
  return res.json();
}
