import { createClient } from '@supabase/supabase-js'

const supabaseUrl = 'https://qwomlwcudldjsbtceasu.supabase.co'
const supabaseKey =
  'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InF3b21sd2N1ZGxkanNidGNlYXN1Iiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0NTA5NzYwNSwiZXhwIjoyMDYwNjczNjA1fQ.Pe0Wf37mvGHzQMfZ6RHHXXGg5jClaGl2-rE9P_LcIkg'
export const supabase = createClient(supabaseUrl, supabaseKey)
