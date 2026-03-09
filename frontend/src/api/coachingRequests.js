const BASE = '';

export async function createRequest(memberId, trainerId) {
  const res = await fetch(`${BASE}/api/coaching-requests`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ memberId, trainerId }),
  });
  if (!res.ok) throw new Error(await res.text());
  return res.json();
}

export async function getRequestsByMember(memberId) {
  const res = await fetch(`${BASE}/api/coaching-requests?memberId=${memberId}`);
  if (!res.ok) throw new Error(await res.text());
  return res.json();
}

export async function getRequestsByTrainer(trainerId) {
  const res = await fetch(`${BASE}/api/coaching-requests?trainerId=${trainerId}`);
  if (!res.ok) throw new Error(await res.text());
  return res.json();
}

export async function updateRequestStatus(requestId, status) {
  const res = await fetch(`${BASE}/api/coaching-requests/${requestId}/status`, {
    method: 'PATCH',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ status }),
  });
  if (!res.ok) throw new Error(await res.text());
  return res.json();
}
