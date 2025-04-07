<script setup>

</script>

<template>
<div class="custom-container mt-4">
    <div class="card p-4">
        <h3>Làm thế nào để căn giữa một div bằng Bootstrap 5?</h3>
        <p class="text-muted">Được hỏi bởi <strong>User123</strong> - 2 giờ trước</p>
        <p class="lead">Mình đang cố gắng căn giữa một div bằng Bootstrap 5 nhưng không hoạt động. Có ai hướng dẫn giúp mình không?</p>
        <div>
            <span class="badge bg-secondary">bootstrap</span>
            <span class="badge bg-secondary">css</span>
        </div>
    </div>
    <h4 class="mt-4">Câu trả lời</h4>
    <div class="card p-3 mb-3 answer-card">
        <p><strong>Trả lời bởi DevGuru</strong> - 1 giờ trước</p>
        <p>Bạn có thể sử dụng <code>class="d-flex justify-content-center align-items-center"</code> cùng với <code>min-vh-100</code> để căn giữa một div trong Bootstrap 5.</p>
    </div>
    <div class="card p-3 mb-3 answer-card">
        <p><strong>Trả lời bởi CodeMaster</strong> - 30 phút trước</p>
        <p>Hãy thử sử dụng <code>mx-auto</code> cho div có độ rộng xác định.</p>
    </div>
</div>

<div class="custom-container mt-4">
    <div class="card p-4">
        <div class="d-flex">
            <div class="vote-section me-3">
                <div class="vote-btn text-primary">▲</div>
                <div>0</div>
                <div class="vote-btn text-primary">▼</div>
            </div>
            <div>
                <h3>Lỗi khi hiển thị template trong Django</h3>
                <p class="text-muted">Được hỏi bởi <strong>UserXYZ</strong> - 3 giờ trước</p>
                <p>Mình đang cập nhật dự án Django nhưng gặp lỗi khi hiển thị template. Có ai giúp được không?</p>
                <pre class="code-block"><code class="language-python">Lỗi khi hiển thị template
Trong template /home/user/project/template.html</code></pre>
                <h5>Code liên quan trong template (admin.html):</h5>
                <!-- <pre class="code-block"><code class="language-django">{% block js %}
{{ block.super }}
<script src="{$ static 'js/plugins/toastr.min.js' %}"></script>
<script type="text/javascript">
</script>
{% endblock %}</code></pre> -->
                <h5>Class-based view (PartnerSchoolsRegisterView):</h5>
                <pre class="code-block"><code class="language-python">class PartnerSchoolsRegisterView(AjaxResponseMixin, JSONResponseMixin, TemplateView):
template_name = 'schools/admin.html'

def get_context_data(self, **kwargs):
    ctx = super().get_context_data(**kwargs)
    departamentos = [{
        'pk': pc.pk,
        'code': pc.code,
        'name': '{} {}'.format(pc.code, pc.name)
    } for pc in Departamento.objects.all()]

    ctx.update({
        'departments': json.dumps(departamentos),
        'regions_url': reverse('administration:get_regions'),
    })
    return ctx</code></pre>
            </div>
        </div>
    </div>
</div>
</template>

<style>
  .custom-container {
    width: 80%;
    margin: 0 auto;
  }

  .vote-section {
    text-align: center;
  }
  .vote-btn {
    font-size: 24px;
    cursor: pointer;
  }
  .code-block {
    background-color: #f8f9fa;
    padding: 10px;
    border-radius: 5px;
    overflow-x: auto;
  }
</style>
