/**
 * 문서 관리를 위한 프론트엔드 모듈
 * IIFE 패턴을 사용하여 전역 네임스페이스 오염 방지
 *
 * @author Hanbit Kang
 * @version 1.0
 * @since 2024-11-20
 */
const DocumentManager = (function() {

    // URL 상수 정의
    const URLS = {
        UPDATE: '/documentation/documents/update',
        DOWNLOAD: '/documentation/documents/download',
        TREE: '/documentation/documents/api/tree',
    };

    return {
        /**
         * 모듈 초기화
         * 이벤트 바인딩 수행
         */
        init: function() {
            this.bindEvents();
            // 트리는 별도로 초기화하도록 변경
            this.loadTreeData();
        },
        loadTreeData: function() {
            this.showLoading();

            $.ajax({
                url: URLS.TREE,
                method: 'GET',
                success: (response) => {
                    this.initializeTree(response);
                },
                error: (xhr) => {
                    this.showAlert('danger', '문서 트리 로딩 중 오류가 발생했습니다.');
                },
                complete: () => {
                    this.hideLoading();
                }
            });
        },

        /**
         * 이벤트 핸들러 바인딩
         * - 업데이트 버튼 클릭 이벤트
         * - 검색 입력 이벤트 (디바운싱 적용)
         */
        bindEvents: function() {
            // 업데이트 버튼 클릭 이벤트
            $('#updateBtn').on('click', this.handleUpdate.bind(this));

            // 검색 입력 이벤트 (300ms 디바운싱)
            let searchTimeout;
            $('#searchInput').on('keyup', function() {
                clearTimeout(searchTimeout);
                searchTimeout = setTimeout(() => {
                    const searchText = $(this).val();
                    $('#documentTree').jstree(true).search(searchText);
                }, 300);
            });
        },

        /**
         * 문서 업데이트 처리
         * AJAX를 통해 서버에 업데이트 요청
         * 성공/실패에 따른 알림 표시
         */
        handleUpdate: function() {
            this.showLoading();

            $.ajax({
                url: URLS.UPDATE,
                method: 'POST',
                success: (response) => {
                    if (response.status === 'success') {
                        this.showAlert('success', response.message);
                        setTimeout(() => location.reload(), 1500);
                    } else {
                        this.showAlert('danger', response.message);
                    }
                },
                error: (xhr) => {
                    this.showAlert('danger', '문서 업데이트 중 오류가 발생했습니다.');
                },
                complete: () => {
                    this.hideLoading();
                }
            });
        },

        /**
         * jsTree 초기화 및 설정
         * 폴더/파일 구조를 트리 형태로 표시
         *
         * @param {Array} treeData 트리 구조 데이터
         */
        initializeTree: function(treeData) {
            if (!treeData) return;
            const $documentTree = $('#documentTree')

            $documentTree.jstree({
                'core': {
                    'data': treeData,
                    'themes': {
                        'responsive': true,
                        'variant': 'large'
                    },
                    'multiple': false,  // 단일 선택만 허용
                    'expand_selected_onload': true,  // 선택 시 자동 확장
                    'dblclick_toggle': false,  // 더블클릭 토글 비활성화
                },
                'plugins': ['types', 'wholerow', 'search'],  // state 플러그인 추가
                'types': {
                    'default': {
                        'icon': 'fas fa-file text-primary'
                    },
                    'folder': {
                        'icon': 'fas fa-folder text-warning'
                    },
                    'file': {
                        'icon': 'fas fa-file text-primary'  // 기본 파일 아이콘
                    },
                    'pdf': {  // PDF 파일용 타입 추가
                        'icon': 'fas fa-file-pdf text-danger'
                    }
                },
                'search': {
                    'show_only_matches': true,
                    'show_only_matches_children': true
                }
            }).on('select_node.jstree', (e, data) => {
                // 파일 노드 선택 시 다운로드 URL로 이동
                const node = data.node;
                if (node.type === 'folder') {
                    // 폴더인 경우 토글
                    $documentTree.jstree(true).toggle_node(node);
                } else if (node.original.type === 'file' && node.original.downloadUrl) {
                    window.location.href = node.original.downloadUrl;
                }
            });

            // PDF 파일 타입 설정
            $documentTree.on('loaded.jstree', function() {
                const tree = $(this).jstree(true);
                tree.get_json('#', {flat: true}).forEach(node => {
                    if (node.text.toLowerCase().endsWith('.pdf')) {
                        tree.set_type(node.id, 'pdf');
                    }
                });
            });
        },

        /**
         * 로딩 스피너 표시
         */
        showLoading: function() {
            $('#loadingSpinner').removeClass('d-none');
        },

        /**
         * 로딩 스피너 숨김
         */
        hideLoading: function() {
            $('#loadingSpinner').addClass('d-none');
        },

        /**
         * 알림 메시지 표시
         * Bootstrap 알림 컴포넌트 사용
         *
         * @param {string} type 알림 타입 ('success' | 'danger')
         * @param {string} message 표시할 메시지
         */
        showAlert: function(type, message) {
            const alertHtml = `
                <div class="alert alert-${type} alert-dismissible fade show" role="alert">
                    ${message}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            `;
            $('#alertContainer').html(alertHtml);
        }
    };
})();
